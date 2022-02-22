package com.bilgeadam.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.bilgeadam.dto.request.GetAllPostByUserIdDto;
import com.bilgeadam.dto.request.SavePostDto;
import com.bilgeadam.repository.IPostRepository;
import com.bilgeadam.repository.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final IPostRepository repository;
    private final S3ManagerService s3ManagerService;

    public Post save(Post item){
        return repository.save(item);
    }

    public Optional<Post> saveDto(SavePostDto dto, MultipartFile file){
        String mediaUrl;
        if(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))
            mediaUrl = UUID.randomUUID().toString()+".png";
        else
            mediaUrl = UUID.randomUUID().toString()+".mp4";
        Optional<PutObjectResult> result = s3ManagerService.putObject(mediaUrl, file);
        if(result.isPresent()){
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
                    .postmedia(mediaUrl)
                    .build();
            return Optional.of(repository.save(post));
        }

        return Optional.empty();
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

    public List<Post> findByUserId(GetAllPostByUserIdDto dto){
        return repository.findByUserid(dto.getUserid());
    }


}
