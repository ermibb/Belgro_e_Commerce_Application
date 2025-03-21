package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Domain.USER_ROLE;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Response.SignupRequest;
import com.ecommerce.belgro.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Returns the user profile of the user who is currently logged in
     *
     * @param jwt the JWT token of the user
     * @return the user profile
     * @throws Exception if the user is not found
     */
    @GetMapping("/user/profile")
    public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization") String jwt) throws Exception {
     User user = userService.findByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

}
