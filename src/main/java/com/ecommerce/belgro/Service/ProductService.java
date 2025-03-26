package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Exceptions.ProductException;
import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request, Seller seller);
    public void deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product>searchProducts(String query);
    public Page<Product> getAllProducts(
            String category,
           String brand,
           String colors,
           String sizes,
           Integer minPrice,
           Integer maxPrice,
           Integer minDiscount,
           String sort,
           String stock,
           Integer pageNumber
    );
    List<Product> getProductsBySeller(Long sellerId);
}
