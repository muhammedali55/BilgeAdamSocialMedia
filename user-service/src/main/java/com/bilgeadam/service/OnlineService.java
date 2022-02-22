package com.bilgeadam.service;

import com.bilgeadam.repository.IOnlineRepository;
import com.bilgeadam.repository.entity.Online;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OnlineService {
    private final IOnlineRepository repository;
    public Online save(Online Online){
        return repository.save(Online);
    }
    public Online update(Online Online){
        return repository.save(Online);
    }
    public boolean delete(String id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public List<Online> findAll(){
        return repository.findAll();
    }
}
