package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Order;
import com.cifer.ecommerce.model.Product;

import java.util.List;

public interface IOrderService {
    Order getOrderInformationBasedOnOrderedProducts(List<Product> productList);
    void deleteOrderedProduct(Long id, String name);
    Order updateOrderedProductQuantity(int quantity);
}
