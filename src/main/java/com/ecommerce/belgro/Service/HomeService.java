package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Home;
import com.ecommerce.belgro.Model.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
