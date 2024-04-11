package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.model.Product;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final IImageService imageService;

    @Override
    public Product addProduct(String name, String description, BigDecimal price, BigDecimal stock,
                              MultipartFile file) throws IOException {
        Product newProduct = new Product();
        Optional<List<String>> allExistedProductName = repository.getAllName();
        for (int i = 0; i < allExistedProductName.get().size(); i++) {
            if (name.equalsIgnoreCase(allExistedProductName.get().get(i))) {
                return null;
            }
        }
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        String imageType = "Product Image";
        ImageData data = imageService.uploadImageToFileSystem(file, imageType);
        newProduct.setData(data);
        return repository.save(newProduct);
    }

    @Override
    public String deleteProduct(String name) {
        Optional<Product> deletedProduct = repository.findByName(name);
        if (deletedProduct.isPresent()) {
            Product preDeletedProduct = deletedProduct.get();
            repository.deleteByName(name);
            return preDeletedProduct.getName() + " has been deleted";
        }
        return "Nothing deleted, maybe product's name does not exist";
    }

    @Override
    public String updateProductInformation(String name, String description, BigDecimal price, BigDecimal stock) {
        Optional<Product> oldProduct = repository.findByName(name);
        Optional<List<String>> allExistedProductName = repository.getAllName();
        for (int i = 0; i < allExistedProductName.get().size(); i++) {
            if (name.equalsIgnoreCase(allExistedProductName.get().get(i))) {
                return null;
            }
        }
        if (oldProduct.isPresent()) {
            Product newProduct = oldProduct.get();
            if (!Objects.equals(name, "")) {
                newProduct.setName(name);
            }
            if (!Objects.equals(description, "")) {
                newProduct.setDescription(description);
            }
            BigDecimal bigDecimalValue = new BigDecimal(0);
            int comparePriceResult = price.compareTo(bigDecimalValue);
            if (comparePriceResult > 0) {
                newProduct.setPrice(price);
            }

            int compareStockResult = price.compareTo(bigDecimalValue);
            if (compareStockResult >= 0) {
                newProduct.setStock(stock);
            }
            return "Update successfully";
        }

        return "Product does not exist";
    }

    @Override
    public String updateProductImage(String name, String fileName, MultipartFile file) throws IOException {
        Optional<Product> updatedProduct = repository.findByName(name);
        ImageData imageData = imageService.updateImageInFileSystem(fileName, file);
        Product newImageProduct = updatedProduct.get();
        newImageProduct.setData(imageData);
        repository.save(newImageProduct);
        return "Update successfully";
    }

    @Override
    public List<Product> getProductContainName(String name) {
        return repository.findUsingName(name);
    }

    @Override
    public List<Product> getAllExistingProduct() {
        return repository.findAll();
    }
}
