package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Model.VerificationCode;
import com.ecommerce.belgro.Repository.VerificationCodeRepository;
import com.ecommerce.belgro.Request.LoginOtpRequest;
import com.ecommerce.belgro.Request.LoginRequest;
import com.ecommerce.belgro.Response.ApiResponse;
import com.ecommerce.belgro.Response.AuthResponse;
import com.ecommerce.belgro.Service.AuthService;
import com.ecommerce.belgro.Service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;


//    @PostMapping("/sent/login-otp")
//    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
//
//        authService.sentLoginOtp(req.getEmail(), req.getRole());
//        ApiResponse response = new ApiResponse();
//        response.setMessage("Otp sent Successfully");
//        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
//    }

@PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest loginRequest) throws Exception {
        String otp = loginRequest.getOtp();
        String email = loginRequest.getEmail();
//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
//            throw  new Exception("Wrong OTP .... ");
//        }
        loginRequest.setEmail("seller_"+email);
         AuthResponse authResponse = authService.signIn(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }

}
