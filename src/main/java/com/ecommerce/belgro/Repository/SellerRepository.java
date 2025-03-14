package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String Email);
}
