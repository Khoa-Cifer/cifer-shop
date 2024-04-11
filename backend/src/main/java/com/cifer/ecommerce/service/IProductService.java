package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    //This task is for seller
    Product addProduct(String name, String description, BigDecimal price, BigDecimal stock,
                       MultipartFile file) throws IOException;
    String deleteProduct(String name);
    String updateProductInformation(String name, String description, BigDecimal price, BigDecimal stock);
    String updateProductImage(String name, String fileName, MultipartFile file) throws IOException;

    //Else, admin and buyer can use there features:

    //Return all product which contain the string input
    List<Product> getProductContainName(String name);

    //Specially for administrator
    List<Product> getAllExistingProduct();
}
