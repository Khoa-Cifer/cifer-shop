package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Order;
import com.cifer.ecommerce.model.Product;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.OrderRepository;
import com.cifer.ecommerce.repository.ProductRepository;
import com.cifer.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    @Autowired
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Order getOrderInformationBasedOnOrderedProducts(List<Product> productList) {
        return null;
    }

    @Override
    public Order deleteOrderedProduct(String userEmail, Long orderId, String name) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Order order = user.getOrderSet().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Set<Product> productSet = order.getProductSet();
        if (productSet.contains(product)) {
            productSet.remove(product);
            order.setProductSet(productSet);
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not associated with the order");
        }
    }

    @Override
    public Order updateOrderedProductQuantity(int quantity) {
        return null;
    }

    @Override
    public Order setProductToOrderList(String userEmail, Long orderId, String productName) {
        Set<Product> productSet = null;
        Order order = orderRepository.findById(orderId).get();
        Product product = productRepository.findByName(productName).get();
        User user = userRepository.findByEmail(userEmail).get();

        Date currentDate = new Date();

        productSet = order.getProductSet();

        productSet.add(product);
        order.setProductSet(productSet);
        order.setOrderDate(currentDate);
        order.setUser(user);

        return orderRepository.save(order);
    }
}
