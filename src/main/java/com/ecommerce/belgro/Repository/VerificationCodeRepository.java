package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String mail);
    VerificationCode findByOtp(String otp);
}
