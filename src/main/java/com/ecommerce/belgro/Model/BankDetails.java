package com.ecommerce.belgro.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {
    private String accountNumber;
    private String accountHolderName;
    //private String bankName;
    private String ifscCode;//change to swift code of a bank but  Indian Financial System Code (IFSC) is a unique 11-character alphanumeric code that identifies the branch for online financial transactions through NEFT, RTGS, and IMPS.
}
