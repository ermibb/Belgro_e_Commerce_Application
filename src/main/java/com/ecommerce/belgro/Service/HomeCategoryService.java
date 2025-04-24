package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.HomeCategory;

import java.util.List;

public interface HomeCategoryService {
    HomeCategory createHomeCategory(HomeCategory homeCategory);
    List<HomeCategory> createCategories(List<HomeCategory> categories);
    HomeCategory updateHomeCategory(Long id, HomeCategory homeCategory) throws Exception;
    List<HomeCategory> getAllHomeCategories();
}
