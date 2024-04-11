package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Query("SELECT w.id FROM Wishlist w WHERE w.user.email = :userEmail && w.product.name = :productName")
    Long findIdByEmailAndProductName(String userEmail, String productName);
}
