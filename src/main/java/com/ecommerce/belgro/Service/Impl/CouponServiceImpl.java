package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.Coupon;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Repository.CartRepository;
import com.ecommerce.belgro.Repository.CouponRepository;
import com.ecommerce.belgro.Repository.UserRepository;
import com.ecommerce.belgro.Service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {

        Coupon coupon=couponRepository.findByCode(code);

        Cart cart=cartRepository.findByUserId(user.getId());

        if(coupon==null){
            throw new Exception("coupon not valid");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new Exception("coupon already used");
        }
        if(orderValue<coupon.getMinimumOrderValue()){
            throw new Exception("valid for minimum order value"+coupon.getMinimumOrderValue());
        }
        if (coupon.isActive() && LocalDate.now().isBefore(coupon.getValidityEndDate())) {
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            double discountPrice = cart.getTotalSellingPrice()*coupon.getDiscountPercentage()/100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountPrice);
            cart.setCouponCode(code);
            cartRepository.save(cart);
            return cart;
        }
        throw new Exception("coupon not valid");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {
        Coupon coupon=couponRepository.findByCode(code);
        if(coupon==null){
            throw new Exception("Coupon not found...");
        }
        Cart cart=cartRepository.findByUserId(user.getId());

        double discountedPrice = ( cart.getTotalSellingPrice()*coupon.getDiscountPercentage());

        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountedPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCouponById(Long id) throws Exception {
        return couponRepository.findById(id).orElseThrow(() ->new Exception("coupon not found with id " + id));
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {
        findCouponById(id);
        couponRepository.deleteById(id);
    }
}
