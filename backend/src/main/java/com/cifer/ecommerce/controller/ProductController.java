package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.model.Product;
import com.cifer.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    private final IProductService productService;

    @PostMapping("/create/new-product/{name}")
    public ResponseEntity<Product> productCreation(
            @PathVariable("name") String name,
            @RequestParam("desc") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stock") BigDecimal stock,
            @RequestParam("image") MultipartFile file) throws IOException {
        Product product = productService.addProduct(name, description, price, stock, file);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/product-data/{name}")
    public ResponseEntity<String> updateProductInformation(
            @PathVariable(value = "name") String name,
            @RequestParam(value = "desc", required = false) String description,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "stock", required = false) BigDecimal stock) {
        String updatedProductInformation = productService.updateProductInformation(
                name, description, price, stock);
        return ResponseEntity.ok(updatedProductInformation);
    }

    @PutMapping("/update/product-image/{name}")
    public ResponseEntity<String> updateProductAvatar(
            @PathVariable("name") String name,
            @RequestParam("fileName") String fileName,
            @RequestParam("image")MultipartFile file) throws IOException {
        String updateProductAvatar = productService.updateProductImage(name, fileName, file);
        return ResponseEntity.ok(updateProductAvatar);
    }

    @DeleteMapping("/delete/product-data/{name}")
    public ResponseEntity<String> deleteProductData(@PathVariable String name) {
        String deletedProduct = productService.deleteProduct(name);
        return ResponseEntity.ok(deletedProduct);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllExistingProduct();
        return ResponseEntity.ok(products);
    }
}
