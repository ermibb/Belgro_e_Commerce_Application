package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Domain.USER_ROLE;
import com.ecommerce.belgro.Request.LoginRequest;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Request.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req) throws Exception;
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    AuthResponse signIn(LoginRequest loginRequest) throws Exception;

}
