package com.ecommerce.belgro.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private  String error;
    private  String details;
    private LocalDateTime timestamp;
}
