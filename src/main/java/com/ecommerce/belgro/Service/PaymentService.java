package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Order;
import com.ecommerce.belgro.Model.PaymentOrder;
import com.ecommerce.belgro.Model.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId);
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean proceedPaymentOrder(
            PaymentOrder paymentOrder,
            String paymentId,
            String paymentLinkId
                               ) throws RazorpayException;
    PaymentLink createRazorpayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException;
    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
