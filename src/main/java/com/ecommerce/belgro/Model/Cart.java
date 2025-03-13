package com.ecommerce.belgro.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
    private double totalSellingPrice;
    private int totalItem;
    private double totalMrpPrice; //MRP stands for Manufacturing Resource Planning. It is a computerized system to manage the production and inventory of goods. MRP can be used in different types of industries like manufacturing, retail, and logistics.
    private int discount;
    private String couponCode;
}
