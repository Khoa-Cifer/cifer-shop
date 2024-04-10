package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.service.IImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class ImageController {
    @Autowired
    private IImageService service;

    @PostMapping("/fileSystem")
    public ResponseEntity<ImageData> uploadImageToFIleSystem(
            @RequestParam("image") MultipartFile file, @RequestParam("imageType") String imageType) throws IOException {
        ImageData uploadImage = service.uploadImageToFileSystem(file, imageType);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{id}")
    public ResponseEntity<byte[]> downloadImageFromFileSystem(
            @PathVariable Long imageId, @RequestParam("userId") Long userId) throws IOException {
        byte[] imageData=service.getImageFromFileSystem(imageId, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @PutMapping("/fileSystem/update/{fileName}")
    public ResponseEntity<ImageData> deleteImageInFileSystem(@PathVariable String fileName, @RequestParam("image")MultipartFile file) throws IOException {
        ImageData imagePath = service.updateImageInFileSystem(fileName, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imagePath);
    }
}
