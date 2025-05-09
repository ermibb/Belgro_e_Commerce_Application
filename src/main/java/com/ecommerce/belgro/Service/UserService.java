package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
