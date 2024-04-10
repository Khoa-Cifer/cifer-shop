package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ImageData uploadImageToFileSystem(MultipartFile file, String imageType) throws IOException;
    byte[] getImageFromFileSystem(Long imageId, Long userId) throws IOException;
    ImageData updateImageInFileSystem(String fileName, MultipartFile file) throws IOException;
    List<String> getDuplicatedImageInFileSystem();
    int getTotalImageInFileSystem();
}
