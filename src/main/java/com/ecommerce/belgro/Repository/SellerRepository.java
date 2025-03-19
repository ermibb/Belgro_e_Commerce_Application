package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Domain.AccountStatus;
import com.ecommerce.belgro.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String Email);
    List<Seller> findByAccountStatus(AccountStatus status);
}
