package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    //This task is for seller
    Product addProduct(Long id, String name, String description, BigDecimal price, BigDecimal stock,
                       MultipartFile file) throws IOException;
    void deleteProduct(Long id);
    String updateProductInformation(Long id, String name, String description, BigDecimal price, BigDecimal stock);
    String updateProductImage(Long id, String fileName, MultipartFile file);

    //Else, admin and buyer can use there features:
    List<Product> getProductByName();

    //Specially for administrator
    List<Product> getAllExistingProduct();
}
