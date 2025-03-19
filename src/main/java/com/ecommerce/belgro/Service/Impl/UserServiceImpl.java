package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Config.JwtProvider;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Repository.UserRepository;
import com.ecommerce.belgro.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;



    @Override
    public User findByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFormJwtToken(jwt);
        User user = this.findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new Exception("User not found with email - "+email);
        }
        return user;
    }
}
