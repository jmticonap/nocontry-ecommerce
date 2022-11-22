package com.nocontry.ecommerce.controllers;

import com.nocontry.ecommerce.dto.AuthCredentials;
import com.nocontry.ecommerce.dto.AuthResponse;
import com.nocontry.ecommerce.dto.RegisterDto;
import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.Role;
import com.nocontry.ecommerce.repositories.RoleRepo;
import com.nocontry.ecommerce.repositories.UserRepo;
import com.nocontry.ecommerce.security.TokenUtils;
import com.nocontry.ecommerce.security.UserDetailsImpl;
import com.nocontry.ecommerce.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {


    private AuthenticationManager authenticationManager;

    private UserRepo userRepo;
    private final UserService userService;

    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthCredentials login) throws Exception {
        Authentication authObj;
        AppUser user = userService.getUser(login.getEmail());

        try{
            UsernamePasswordAuthenticationToken auth_token = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    login.getPassword(),
                    user.getRoles()
            );
            authObj = authenticationManager.authenticate(auth_token);
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

        Role role = roleRepo.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(role));

        userRepo.save(user);

        String log_message = String.format("User %s registered success", user.getName());
        log.info(log_message);
        return new ResponseEntity<>(log_message, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUser> findUser(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

}
