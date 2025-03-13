package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Repository.UserRepository;
import com.ecommerce.belgro.Response.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.ACCEPTED);
    }
}
