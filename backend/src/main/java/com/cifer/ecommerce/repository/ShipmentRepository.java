package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
