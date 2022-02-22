package com.bilgeadam.service;

import com.bilgeadam.repository.ILikeRepository;
import com.bilgeadam.repository.entity.Like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService{

    private final ILikeRepository repository;
    
    public Like save(Like item){
        return repository.save(item);
    }
    public Like update(Like item){
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
    public List<Like> findAll(){
        return repository.findAll();
    }
}
