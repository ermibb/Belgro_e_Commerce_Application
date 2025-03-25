package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request, Seller seller);
    public void deleteProduct(Long productId);
    public Product updateProduct(Long productId, Product product);
    Product findProductById(Long productId);
    List<Product>searchProducts();
    public Page<Product> getAllProducts(
           String brand,
           String colors,
           String sizes,
           Integer minPrice,
           Integer maxPrice,
           Integer minDiscount,
           String sort,
           Integer stock,
           Integer pageNumber
    );
    List<Product> getProductsBySeller(Long sellerId);
}
