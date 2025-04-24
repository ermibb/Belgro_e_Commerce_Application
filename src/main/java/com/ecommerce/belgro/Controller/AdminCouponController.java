package com.ecommerce.belgro.Controller;

import com.ecommerce.belgro.Model.Cart;
import com.ecommerce.belgro.Model.Coupon;
import com.ecommerce.belgro.Model.User;
import com.ecommerce.belgro.Service.CartService;
import com.ecommerce.belgro.Service.CouponService;
import com.ecommerce.belgro.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class AdminCouponController {
    private final CouponService CouponService;
    private final UserService userService;
    private final CartService cartService;
    private final CouponService couponService;
    @PostMapping("/apply") // Note: There might be a typo in the path, perhaps intended as "/apply"
            public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt ) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Cart cart;

        if(apply.equals("true")){
            cart = couponService.applyCoupon(code,orderValue,user);
        }
        else {
            cart = couponService.removeCoupon(code,user);
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon createdCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createdCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok().body("Coupon deleted successfully");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.findAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}
