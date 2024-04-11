package com.cifer.ecommerce.service;


public interface IWishlistService {
    String addProductToWishlistByUserEmail(String userEmail, String productName);
    String deleteProductFromWishlistByUserEmail(String userEmail, String productName);

}
