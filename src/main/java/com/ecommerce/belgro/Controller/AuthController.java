package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Domain.USER_ROLE;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Model.VerificationCode;
import com.ecommerce.belgro.Repository.UserRepository;
import com.ecommerce.belgro.Request.LoginRequest;
import com.ecommerce.belgro.Response.ApiResponse;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Response.SignupRequest;
import com.ecommerce.belgro.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
   private final UserRepository userRepository;
   private final AuthService authService;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest signupRequest) throws Exception {

        String jwt = authService.createUser(signupRequest);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("You are registered Successfully");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody VerificationCode req) throws Exception {

        authService.sentLoginOtp(req.getEmail());
        ApiResponse response = new ApiResponse();
        response.setMessage("Otp sent Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> sentOtpHandler(@RequestBody LoginRequest req) throws Exception {

       AuthResponse authResponse = authService.signIn(req);
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }
}
