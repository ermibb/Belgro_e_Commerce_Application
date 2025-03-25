package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Request.CreateProductRequest;
import com.ecommerce.belgro.Service.ProductService;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product createProduct(CreateProductRequest request, Seller seller) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product findProductById(Long productId) {
        return null;
    }

    @Override
    public List<Product> searchProducts() {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String brand, String colors, String sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, Integer stock, Integer pageNumber) {
        return null;
    }

    @Override
    public List<Product> getProductsBySeller(Long sellerId) {
        return null;
    }
}
