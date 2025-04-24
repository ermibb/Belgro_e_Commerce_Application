package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.Coupon;
import com.ecommerce.belgro.Model.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception; // no usages
    Cart removeCoupon(String code, User user) throws Exception; // no usages
    Coupon findCouponById(Long id) throws Exception; // no usages
    Coupon createCoupon(Coupon coupon); // no usages
    List<Coupon> findAllCoupons(); // no usages
    void deleteCoupon(Long id) throws Exception; // no usages
}
