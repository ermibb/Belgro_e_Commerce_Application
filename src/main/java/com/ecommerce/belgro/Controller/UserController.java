package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Domain.USER_ROLE;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Response.SignupRequest;
import com.ecommerce.belgro.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


}
