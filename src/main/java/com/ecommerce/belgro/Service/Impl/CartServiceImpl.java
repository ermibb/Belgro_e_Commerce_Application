package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.CartItem;
import com.ecommerce.belgro.Model.Product;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Repository.CartItemRepository;
import com.ecommerce.belgro.Repository.CartRepository;
import com.ecommerce.belgro.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;


    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
          Cart cart = findUserCart(user);
          CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart,
                  product, size);
        if (isPresent == null) {

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            double totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity * product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());


        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalItem, totalDiscountedPrice));
        cart.setTotalItem(totalItem);
        return cart;
    }
    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            return 0;
        }
        /*
        In the context of retail, "MRP" stands for Maximum Retail Price, which is the highest price a manufacturer
        allows a retailer to charge for a product, and it's often printed on the product packaging.
         */
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int)discountPercentage;
    }
}
