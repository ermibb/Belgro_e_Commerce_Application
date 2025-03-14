package com.ecommerce.belgro.Response;

import com.ecommerce.belgro.Domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
