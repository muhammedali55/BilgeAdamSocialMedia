package com.bilgeadam.service;
import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.FindByAutIdDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.JwtEncodeDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    UserMapper  userMapper;

    @Autowired
    ProfileManager profileManager;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtEncodeDecode jwtEncodeDecode;

    @Autowired
    CacheManager cacheManager;

    /**
     * Kullanıcıyı kayıt eder ve kayıtedilen kullanıcının id bilgisi alınarak geri döndürülür.
     * @param dto
     * @return
     */
    public User saveReturnUser(RegisterRequestDto dto){
        User user = userMapper.toUser(dto);
        User result =  iUserRepository.save(user);
        this.clearCache();
        return result;
    }

    /**
     * Method için den çağırıldığında işlem temizle yapmıyor. Sorunu çözelim.
     */
    //@CacheEvict(cacheNames = "user_findall",allEntries = true)
    public void clearCache(){
        cacheManager.getCache("user_findall").clear();
        System.out.println("Cach Temizlendi...");
    }

    public void save(User user){
        iUserRepository.save(user);
        this.clearCache();
    }


    public void update(User user){
        iUserRepository.save(user);
    }

    public void delete(User user){
        iUserRepository.delete(user);
    }

    @Cacheable(value = "user_findall")
    public List<User> findAll(){
        return iUserRepository.findAll();
    }

    /**
     * Kullanıcı listesi sayfa sayfa şeklinde döndürmek için kullanılır.
     * tüm listesi çekerek sayfalama yapar. tercihe göre kullanılmalıdır.
     * @param page -> hangi sayfayı göstermek istiyorsanız o sayfa numarasını verin.
     * @param pagesize -> sayfada kaç kayıt gösterileceğini verin.
     * @return -> itereble bir liste döndürür.
     */
    public Page<User> findAllPage(int page,int pagesize){
        /**
         * Sayfalama yapmak istediğimiz Pageable nesnesi oluşturulur.
         * burada oluşturmak istediğimiz sayfa ve adedi verilir.
         */
        Pageable pageable = PageRequest.of(page,pagesize);
        return iUserRepository.findAll(pageable);
    }

    /**
     * Slice  -> Şuanki sayfadan sonra başka kayıt var mı? kontrolü yapılır.
     * @param page -> şuanki sayfa numarası
     * @param pagesize -> sayfada kaç kayıt gösterileceğini verin.
     * @param sortparameter -> sıralama yapılacak olan alanın adını verin.
     * @return
     */
    public Slice<User> findAllSlice(int page,int pagesize,String sortparameter,String direction){
        Sort sort = Sort.by(direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,sortparameter);
        Pageable pageable = PageRequest.of(page,pagesize,sort);
        return iUserRepository.findAll(pageable);
    }

    public Slice<User> findAllSlice(Pageable pageable){
        return iUserRepository.findAll(pageable);
    }


    @Cacheable(value = "merhaba_cache")
    public String merhaba(String mesaj){
        try {
            Thread.sleep(3500);
        }catch (Exception e){
        }
        return mesaj;
    }

    public boolean isUser(String username,String password){
       Optional<User> user = iUserRepository.findByUsernameAndPassword(username, password);
       if (user.isPresent())
           return true;
        return false;
    }
    /*
        public  Optional<User> getUser(DoLoginRequestDto dto){
            return iUserRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        }
    */
    public DoLoginResponseDto getProfile(DoLoginRequestDto dto){
        /**
         * Kullancı adı ve şifre den auth Db de ki kullanıcıyı arar.
         */
        Optional<User> user =iUserRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent()){
            /**
             * Eğer kullanıvı var ise, ProfileController a giderek kişiye ait profil id sini getirecek.             *
             */
            long authid = user.get().getId();
            String profileid =   profileManager.findByAuthId(FindByAutIdDto.builder().authid(authid).build()).getBody();
           // String EncodedProfileId = jwtEncodeDecode.getEncryptUUID(profileid);
            Optional<String> token = jwtTokenManager.createToken(profileid);

            /**
             * Eğer dönen değer, "" ise farklı dolu ise farklı işlem yapılacak.
             */
            if (profileid.equals("")){
                throw new AuthServiceException(ErrorType.AUTH_KULLANICI_SIFRE_HATASI,"Profil Id bilgisi alınamadı");
            }else{
                if(token.isPresent())
                    return DoLoginResponseDto.builder().profileid(profileid).token(token.get()).error(200).build();
                else
                    throw new AuthServiceException(ErrorType.AUTH_GECERSIZ_TOKEN,"Geçersiz Token Denemesi");
            }
        }
        throw new AuthServiceException(ErrorType.AUTH_KULLANICI_SIFRE_HATASI,"Oturum Bilgileri alanamadı");
    }

    public boolean verifyToken(String token){
        return jwtTokenManager.validateToken(token);
    }
}
