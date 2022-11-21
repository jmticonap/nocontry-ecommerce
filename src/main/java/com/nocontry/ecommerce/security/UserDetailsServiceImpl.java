package com.nocontry.ecommerce.security;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepo
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username not found")
                );
        log.info( user.toString() );
        return new UserDetailsImpl(user);
    }

}
