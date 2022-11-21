package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.dto.AuthCredentials;
import com.nocontry.ecommerce.dto.AuthResponse;
import com.nocontry.ecommerce.dto.RegisterDto;
import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.RoleEntity;
import com.nocontry.ecommerce.repositories.RoleRepository;
import com.nocontry.ecommerce.repositories.UserRepository;
import com.nocontry.ecommerce.security.TokenUtils;
import com.nocontry.ecommerce.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthCredentials login) throws Exception {
        Authentication authObj;
        try{
            authObj = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authObj);
        } catch (BadCredentialsException e){
            throw new Exception("Credentials invalid");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authObj.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername());

        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("This user already taken", HttpStatus.BAD_REQUEST);
        }
        AppUser user = new AppUser();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(
                passwordEncoder
                        .encode(registerDto.getPassword())
        );

        RoleEntity roleEntity = roleRepo.findByName("ROLE_USER");
        user.setRoleEntities(Collections.singletonList(roleEntity));

        userRepo.save(user);

        String log_message = String.format("User %s registered success", user.getName());
        log.info(log_message);
        return new ResponseEntity<>(log_message, HttpStatus.CREATED);
    }

    @GetMapping("/saludo")
    public ResponseEntity<String> saludo() {
        return new ResponseEntity<>("HOLA", HttpStatus.OK);
    }

}
