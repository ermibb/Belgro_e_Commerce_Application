package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
