package com.cifer.ecommerce.repository;

import com.cifer.ecommerce.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);

    @Query("SELECT i.name FROM ImageData i")
    List<String> getDuplicateImageName();

    @Query("SELECT COUNT(*) FROM ImageData i")
    int getTotalImages();

    @Query("SELECT i FROM ImageData i JOIN User u " +
            "ON i.id = u.id " +
            "WHERE i.id = :imageId AND u.id = :userId")
    Optional<ImageData> findByImageByIdAndUserId(Long imageId, Long userId);
}
