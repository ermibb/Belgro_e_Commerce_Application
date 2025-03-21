package com.ecommerce.belgro.Request;

import com.ecommerce.belgro.Domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private  String otp;
    private USER_ROLE role;
}
