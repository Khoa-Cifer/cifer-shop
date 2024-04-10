package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT u.email FROM User u")
    Optional<List<String>> getAllEmail();

    void deleteByEmail(String email);
}
