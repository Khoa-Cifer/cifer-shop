package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Order;
import com.cifer.ecommerce.model.Product;

import java.util.List;

public interface IOrderService {
    Order getOrderInformationBasedOnOrderedProducts(List<Product> productList);
    Order deleteOrderedProduct(String userEmail, Long orderId, String productName);
    Order updateOrderedProductQuantity(int quantity);
    Order setProductToOrderList(String userEmail, Long orderId, String productName);
}
