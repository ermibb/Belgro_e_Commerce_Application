package com.ecommerce.belgro.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(SellerException.class) //ExceptionHandler.
    public ResponseEntity<ErrorDetail> sellerExceptionHandler(SellerException se, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(se.getMessage());
        errorDetail.setDetails(request.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class) //ExceptionHandler.
    public ResponseEntity<ErrorDetail> productExceptionHandler(SellerException se, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setError(se.getMessage());
        errorDetail.setDetails(request.getDescription(false));
        errorDetail.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
