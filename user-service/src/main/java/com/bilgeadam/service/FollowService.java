package com.bilgeadam.service;

import com.bilgeadam.repository.IFollowRepository;
import com.bilgeadam.repository.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final IFollowRepository repository;

    public Follow save(Follow Follow){
        return repository.save(Follow);
    }
    public Follow update(Follow Follow){
        return repository.save(Follow);
    }
    public boolean delete(String id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public List<Follow> findAll(){
        return repository.findAll();
    }
}
