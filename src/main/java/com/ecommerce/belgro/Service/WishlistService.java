package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Model.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
