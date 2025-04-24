package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.CartItem;
import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.User;

public interface CartService {
    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );

    public Cart findUserCart(User user);
}
