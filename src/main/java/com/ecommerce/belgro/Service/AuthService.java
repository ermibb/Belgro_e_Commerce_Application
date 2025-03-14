package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Request.LoginRequest;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Response.SignupRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    String createUser(SignupRequest req) throws Exception;
    void sentLoginOtp(String email) throws Exception;
    AuthResponse signIn(LoginRequest loginRequest);

}
