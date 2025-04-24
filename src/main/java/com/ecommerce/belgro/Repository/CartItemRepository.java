package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.CartItem;
import com.ecommerce.belgro.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
