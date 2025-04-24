package com.ecommerce.belgro.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
   // @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    private Product product;

    private String size;

    private int quantity;

    private double mrpPrice;

    private double sellingPrice;

    private Long userId;


}
