package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Product;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.model.Wishlist;
import com.cifer.ecommerce.repository.ProductRepository;
import com.cifer.ecommerce.repository.UserRepository;
import com.cifer.ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WishlistService implements IWishlistService {
    @Autowired
    private final WishlistRepository wishlistRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public String addProductToWishlistByUserEmail(String userEmail, String productName) {
        //user find product then add to the wishlist
        Optional<Product> product = productRepository.findByName(productName);
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (product.isPresent() && user.isPresent()) {
            Product savedProduct = product.get();
            User savedUser = user.get();
            Wishlist wishlistData = new Wishlist();
            wishlistData.setUser(savedUser);
            wishlistData.setProduct(savedProduct);
            wishlistRepository.save(wishlistData);
            return "Update successfully";
        } else if (user.isPresent()) {
            return "Unsuccessful because product you need does not exist";
        }
        return "Email does not exist";
    }

    @Override
    public String deleteProductFromWishlistByUserEmail(String userEmail, String productName) {
        //User delete product from wishlist
        Optional<Product> product = productRepository.findByName(productName);
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (product.isPresent() && user.isPresent()) {
            Long wishlistId = wishlistRepository.findIdByEmailAndProductName(userEmail, productName);
            if (wishlistId == null) {
                return "This user does not set this product to wishlist";
            } else {
                wishlistRepository.deleteById(wishlistId);
            }
            return "Delete successfully";
        } else if (user.isPresent()) {
            return "Unsuccessful because product you need does not exist";
        }
        return "Email does not exist";
    }
}
