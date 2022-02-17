package com.bilgeadam.service;

import com.bilgeadam.repository.IProfileReposity;
import com.bilgeadam.repository.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElasticService {

    private final IProfileReposity iProfileReposity;

    public void save(Profile profile) {
        iProfileReposity.save(profile);
    }
    public void update(Profile profile) {
        iProfileReposity.save(profile);
    }
    public void delete(Profile profile) {
        iProfileReposity.delete(profile);
    }
    public Optional<Profile> findById(String id) {
        return iProfileReposity.findById(id);
    }
    public List<Profile> findByFirstNameLike(String firstName) {
        return iProfileReposity.findByFirstnameLike(firstName);
    }

}
