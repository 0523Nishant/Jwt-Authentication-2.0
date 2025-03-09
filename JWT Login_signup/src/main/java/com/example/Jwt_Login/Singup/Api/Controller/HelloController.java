package com.example.Jwt_Login.Singup.Api.Controller;

import com.example.Jwt_Login.Singup.Api.Entity.GeneralEntity;
import com.example.Jwt_Login.Singup.Api.JwtUtility.UserUtility;
import com.example.Jwt_Login.Singup.Api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("greet")
public class HelloController{

    @GetMapping
    public String Greet(){
        return "welcome back";
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserUtility jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody GeneralEntity loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        String token = jwtTokenUtil.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody GeneralEntity user) {

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}
