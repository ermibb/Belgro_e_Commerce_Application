package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Exceptions.ProductException;
import com.ecommerce.belgro.Model.Category;
import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Repository.CategoryRepository;
import com.ecommerce.belgro.Repository.ProductRepository;
import com.ecommerce.belgro.Request.CreateProductRequest;
import com.ecommerce.belgro.Service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(CreateProductRequest request, Seller seller) {
        Category category1 = categoryRepository.findByCategoryId(request.getCategory());

        if (category1 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory());
            category.setLevel(1);
            category1 = categoryRepository.save(category);
        }
        Category category2 = categoryRepository.findByCategoryId(request.getCategory2());
        if (category2 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory2());
            category.setLevel(2);
            category.setParentCategory(category1);
            category2 = categoryRepository.save(category);
        }
        Category category3 = categoryRepository.findByCategoryId(request.getCategory3());
        if (category3 == null) {
            Category category = new Category();
            category.setCategoryId(request.getCategory3());
            category.setLevel(3);
            category.setParentCategory(category2);
            category3 = categoryRepository.save(category);
        }

        int discountPercentage = calculateDiscountPercentage(request.getMrpPrice(),
                request.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(request.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setSellingPrice(request.getSellingPrice());
        product.setImages(request.getImages());
        product.setMrpPrice(request.getMrpPrice());
        product.setSizes(request.getSizes());
        product.setDiscountPercent(discountPercentage);
        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Actual Price should be greater than 0");
        }
        /*
        In the context of retail, "MRP" stands for Maximum Retail Price, which is the highest price a manufacturer
        allows a retailer to charge for a product, and it's often printed on the product packaging.
         */
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int)discountPercentage;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        findProductById(productId);
        product.setId(productId);
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId).orElseThrow(() ->
                new ProductException("Product not found with id " + productId));
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String colors, String sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount, String sort,
                                        String stock, Integer pageNumber) {

        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by category
            if (category != null) {
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
            }

            // Filter by brand
            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
            }

            // Filter by color
            if (colors != null && !colors.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("color"), colors));
            }

            // Filter by size
            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("size"), sizes));
            }

            // Filter by price range
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }

            // Filter by discount
            if (minDiscount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDiscount));
            }

            // Filter by stock
            if (stock != null) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // Add sorting logic
        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "price_low" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").ascending());
                case "price_high" ->
                        PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
        }

        return productRepository.findAll(spec, pageable);
    }

    @Override
    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}
