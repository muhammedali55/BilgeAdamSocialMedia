package com.bilgeadam.graphql.mutation;

import com.bilgeadam.graphql.model.ProfileInput;
import com.bilgeadam.repository.IProfileReposity;
import com.bilgeadam.repository.entity.Profile;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Muttaion Resolver
 * İstenilen değerler ile kayıt,güncelleme ve silme işlemlerinde
 * kullanılmak üzere tasarlanmıştır.
 */

@Component
@RequiredArgsConstructor
public class ProfileMutationResolver implements GraphQLMutationResolver {

    private final IProfileReposity repository;

    public void createProfileElastic(ProfileInput profileInput){
        repository.save(Profile.builder()
                        .country(profileInput.getCountry())
                        .email(profileInput.getEmail())
                        .firstname(profileInput.getFirstname())
                        .lastname(profileInput.getLastname())
                        .city(profileInput.getCity())
                        .profileid(profileInput.getProfileid())
                .build());
    }
}
