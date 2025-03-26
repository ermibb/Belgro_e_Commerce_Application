package com.ecommerce.belgro.Repository;

import com.ecommerce.belgro.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(String categoryId);

}
