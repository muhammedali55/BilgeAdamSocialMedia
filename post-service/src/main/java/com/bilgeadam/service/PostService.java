package com.bilgeadam.service;

import com.bilgeadam.repository.IPostRepository;
import com.bilgeadam.repository.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

private final IPostRepository repository;

    public Post save(Post item){
        return repository.save(item);
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
}
