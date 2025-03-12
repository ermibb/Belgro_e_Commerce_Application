package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping()
    public ResponseEntity<ApiResponse> HomeControllerHandler(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to ecommerce multi vendor system");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
