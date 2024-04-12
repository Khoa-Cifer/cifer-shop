package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.Order;
import com.cifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
