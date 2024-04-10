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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final IImageService imageService;

    @Override
    public Product addProduct(Long id, String name, String description, BigDecimal price, BigDecimal stock,
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
    public void deleteProduct(Long id) {
        Optional<Product> deletedProduct = repository.findById(id);
        if (deletedProduct.isPresent()) {
            repository.deleteById(id);
        }
    }

    @Override
    public String updateProductInformation(Long id, String name, String description, BigDecimal price, BigDecimal stock) {
        return null;
    }

    @Override
    public String updateProductImage(Long id, String fileName, MultipartFile file) {
        return null;
    }

    @Override
    public List<Product> getProductByName() {
        return null;
    }

    @Override
    public List<Product> getAllExistingProduct() {
        return null;
    }
}
