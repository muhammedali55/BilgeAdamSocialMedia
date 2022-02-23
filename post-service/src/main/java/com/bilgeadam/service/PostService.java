package com.bilgeadam.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.dto.request.GetAllPostByUserIdDto;
import com.bilgeadam.dto.request.SavePostDto;
import com.bilgeadam.dto.response.FindAllPostByUserIdResponseDto;
import com.bilgeadam.repository.ICommentRepository;
import com.bilgeadam.repository.IPostRepository;
import com.bilgeadam.repository.entity.Post;
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

    public List<FindAllPostByUserIdResponseDto> findByUserId(GetAllPostByUserIdDto dto){
        List<Post> posts = repository.findByUserid(dto.getUserid());
        List<FindAllPostByUserIdResponseDto> result = new ArrayList<>();
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
        return result;
    }


}
