package com.bilgeadam.service;

import com.bilgeadam.repository.IInterestRepository;
import com.bilgeadam.repository.entity.Interest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final IInterestRepository repository;

    public Interest save(Interest Interest){
        return repository.save(Interest);
    }
    public Interest update(Interest Interest){
        return repository.save(Interest);
    }
    public boolean delete(String id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public List<Interest> findAll(){
        return repository.findAll();
    }
}
