package com.ecommerce.belgro.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotNull
    @Column(unique = true)
    private String categoryId;

    @ManyToOne
    private Category parentCategory;

    private Integer level;

}