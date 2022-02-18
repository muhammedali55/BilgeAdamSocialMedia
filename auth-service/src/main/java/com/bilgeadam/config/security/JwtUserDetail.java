package com.bilgeadam.config.security;

import com.bilgeadam.dto.request.IsProfileExistsDto;
import com.bilgeadam.manager.ProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetail implements UserDetailsService {

    @Autowired
    ProfileManager profileManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserProfileId(String profileId){
        boolean isprofile = profileManager.isProfileExistById(IsProfileExistsDto.builder().profileId(profileId).build()).getBody();
        if(isprofile){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
            return new User(profileId, "", true,
                    true, true, true, grantedAuthorities);
        }
        return null;
    }
}
