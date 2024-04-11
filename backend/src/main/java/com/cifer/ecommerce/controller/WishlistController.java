package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.service.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/wishlists")
public class WishlistController {
    @Autowired
    private final IWishlistService wishlistService;

    @PostMapping("/set-wishlist/new-wishlist")
    public ResponseEntity<String> addToWishlist(
            @RequestParam("email") String email,
            @RequestParam("name") String name) {
        String newWishlistData = wishlistService.addProductToWishlistByUserEmail(email, name);
        return ResponseEntity.ok(newWishlistData);
    }

    @DeleteMapping("/delete-wishlist/{email}&&{name}")
    public ResponseEntity<String> deleteFromWishlist(
            @PathVariable("email") String email,
            @PathVariable("name") String name) {
        String deletedWishListData = wishlistService.deleteProductFromWishlistByUserEmail(email, name);
        return ResponseEntity.ok(deletedWishListData);
    }
}
