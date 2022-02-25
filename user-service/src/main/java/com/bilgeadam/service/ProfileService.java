package com.bilgeadam.service;

import com.bilgeadam.dto.request.FindByIdRequestDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.mapper.ProfileMapper;
import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.secutiry.JwtTokenManager;
import com.bilgeadam.utility.JwtEncodeDecode;
import com.bilgeadam.utility.ResultObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final IProfileRepository repository;
    private final ProfileMapper profileMapper;
    private final JwtEncodeDecode jwtEncodeDecode;
    private final JwtTokenManager jwtTokenManager;
    public String save(ProfileRequestDto dto){
        Profile profile = profileMapper.toProfile(dto);
        repository.save(profile);
        return profile.getId();
    }
    public void update(Profile profile){
        repository.save(profile);
    }
    public void delete(Profile profile){
        repository.delete(profile);
    }
    public List<Profile> findAll(){
        return repository.findAll();
    }

    public Optional<Profile> findByAuthId(long id){
        return repository.findByAuthid(id);
    }

    public Optional<Profile> findById(String id){
        return repository.findById(id);
    }

    public ResultObject findById(FindByIdRequestDto dto){
        ResultObject resultObject;
        boolean iscurrentToken =  jwtTokenManager.validateToken(dto.getToken());
        /**
         * Eğer token doğrulamadan geçemez ise boş liste döndürülür.
         */
        if(!iscurrentToken){
            return ResultObject.builder().status(500).resultCode(3901).build();
        }else{
            /**
             * öncelikle token içinden şifreli profileid alınır. Eğere çözümleme yapılamaz ise boş liste döndürülür.
             */
            Optional<String> encodedProfileID = jwtTokenManager.getProfileId(dto.getToken());
            if(!encodedProfileID.isPresent()){
                return ResultObject.builder().status(500).resultCode(3902).build();
            }else {
                /**
                 * şifreli olarak gelen profileid çözümlenerek kullanılır.
                 */
                String profileID = jwtEncodeDecode.getDecodeUUID(encodedProfileID.get());
                Optional<Profile> profile = repository.findById(profileID);
                if(profile.isPresent()){
                    return ResultObject.builder().status(200).resultCode(2001).result(profile.get()).build();
                }else{
                    return ResultObject.builder().status(500).resultCode(3903).build();
                }
            }
            }

    }

}
