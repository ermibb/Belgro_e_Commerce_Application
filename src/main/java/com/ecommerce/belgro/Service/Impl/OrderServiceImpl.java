package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Domain.OrderStatus;
import com.ecommerce.belgro.Domain.PaymentStatus;
import com.ecommerce.belgro.Model.*;
import com.ecommerce.belgro.Repository.AddressRepository;
import com.ecommerce.belgro.Repository.OrderItemRepository;
import com.ecommerce.belgro.Repository.OrderRepository;
import com.ecommerce.belgro.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if(!user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        /*
        *seller 1 => 4 shirt
        * seller 2 => 2 shirt
        * seller 3 => 3 shirt
         */

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream().collect(
                Collectors.groupingBy(item -> item.getProduct().getSeller().getId())
        );

        Set<Order> orders = new HashSet<>();
        for(Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItem> cartItems = entry.getValue();

            double totalOrderPrice = cartItems.stream().mapToDouble(
                    CartItem::getSellingPrice
            ).sum();

            int totalItem = cartItems.stream().mapToInt(
                    CartItem::getQuantity
            ).sum();

            Order createOrder = new Order();
            createOrder.setUser(user);
            createOrder.setShippingAddress(address);
            createOrder.setSellerId(sellerId);
            createOrder.setTotalMrpPrice(totalOrderPrice);
            createOrder.setTotalSellingPrice(totalOrderPrice);
            createOrder.setTotalItem(totalItem);
            createOrder.setOrderStatus(OrderStatus.PENDING);
            createOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createOrder);
            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();
            for(CartItem cartItem : cartItems){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(cartItem.getMrpPrice());
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setSellingPrice(cartItem.getSellingPrice());
                orderItem.setSize(cartItem.getSize());
                orderItem.setUserId(cartItem.getUserId());

                savedOrder.getOrderItems().add(orderItem);
                OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }

        }
        return orders;
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() ->new Exception("Order not found with id " + id));
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellerOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {
        Order order = findOrderById(orderId);

        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("You don't have permission to cancel this order");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id).orElseThrow(() -> new Exception("Order item not exists with id " + id));
    }
}
