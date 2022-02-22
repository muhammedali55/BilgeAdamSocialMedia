package com.bilgeadam.service;

import com.bilgeadam.repository.IDisLikeRepository;
import com.bilgeadam.repository.entity.Comment;
import com.bilgeadam.repository.entity.DisLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisLikeService {

    private final IDisLikeRepository repository;

    public DisLike save(DisLike item){
        return repository.save(item);
    }
    public DisLike update(DisLike item){
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
    public List<DisLike> findAll(){
        return repository.findAll();
    }
}
