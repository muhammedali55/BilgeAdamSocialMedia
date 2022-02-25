package com.bilgeadam.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.dto.request.GetAllPostByUserIdDto;
import com.bilgeadam.dto.request.SavePostDto;
import com.bilgeadam.dto.response.FindAllPostByUserIdResponseDto;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.repository.IPostRepository;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.security.JwtTokenManager;
import com.bilgeadam.utility.JwtEncodeDecode;
import com.bilgeadam.utility.ResultObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final IPostRepository repository;
    private final S3ManagerService s3ManagerService;
    private final MediaService mediaService;
    private final ICommentRepository iCommentRepository;
    private final JwtTokenManager   jwtTokenManager;
    private final JwtEncodeDecode jwtEncodeDecode;
    public Post save(Post item){
        return repository.save(item);
    }

    public Optional<Post> saveDto(SavePostDto dto){
            Post.Location location = Post.Location.builder()
                    .address(dto.getAddress())
                    .lat(dto.getLat())
                    .lng(dto.getLat()).build();

            Post post = Post.builder()
                    .content(dto.getContent())
                    .userid(dto.getUserid())
                    .username(dto.getUsername())
                    .location(location)
                    .sharedtime(new Date().getTime())
                    .postmedia(dto.getImagename())
                    .build();
            return Optional.of(repository.save(post));

    }

    public Post update(Post item){
        return repository.save(item);
    }
    public boolean delete(String id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public List<Post> findAll(){
        return repository.findAll();
    }

    public ResultObject findByUserId(GetAllPostByUserIdDto dto){
        List<FindAllPostByUserIdResponseDto> result = new ArrayList<>();
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
            }else{
                /**
                 * şifreli olarak gelen profileid çözümlenerek kullanılır.
                 */
               // String profileID = jwtEncodeDecode.getDecodeUUID(encodedProfileID.get());
                List<Post> posts = repository.findByUserid(encodedProfileID.get());

                for (Post post: posts ) {
                    String baseUrl =  post.getPostmedia();
                    Optional<URL> realUrl = mediaService.getGoogleSignedMediaPath(baseUrl,10);
                    if(realUrl.isPresent()){
                        post.setPostmedia(realUrl.get().toString());
                    }else
                        post.setPostmedia("");
                    result.add(
                            FindAllPostByUserIdResponseDto.builder()
                                    .following(true) // TODO: Burada bu post u atan kişiyi takip edip etmediğimizi kontrol etmemiz gerekiyor.
                                    .id(post.getId())
                                    .dislike(post.getDislike())
                                    .like(post.getLike())
                                    .postmediaurl(post.getPostmedia())
                                    .publishat(post.getSharedtime())
                                    .content(post.getContent())
                                    .username(post.getUsername())
                                    .comments(iCommentRepository.findByPostid(post.getId()))
                                    .build()
                    );
                }
                return ResultObject.builder().status(200).resultCode(3001).result(result).build();
            }

        }

    }


}
