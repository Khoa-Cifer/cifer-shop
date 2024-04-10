package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.repository.ImageDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageDataRepository repository;
    private final String FOLDER_PATH = "I:/FullStackApplicationPractice/e-commerce-app/resource/";

    @Override
    public ImageData uploadImageToFileSystem(MultipartFile file, String imageType) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        Date currentDate = new Date();
        ImageData fileData = new ImageData();
        int versionCopy = 0;
        for (int i = 0; i < getTotalImageInFileSystem(); i++) {
            if (file.getOriginalFilename().equalsIgnoreCase(repository.getDuplicateImageName().get(i))) {
                versionCopy++;
            }
        }

        fileData.setName(file.getOriginalFilename());
        fileData.setCreatedTime(currentDate);
        fileData.setType(file.getContentType());
        fileData.setFilePath(filePath);
        fileData.setVersionCopy(versionCopy);
        fileData.setImageType(imageType);

        repository.save(fileData);

        if (fileData.getVersionCopy() == 0) {
            file.transferTo(new File(FOLDER_PATH + file.getOriginalFilename()));
        } else {
            file.transferTo(new File(FOLDER_PATH + file.getOriginalFilename()
                    .substring(0, file.getOriginalFilename().lastIndexOf('.'))
                    + " " + "(" + fileData.getVersionCopy() + ")" +
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'))));
        }

        if (fileData != null) {
            return fileData;
        }
        return null;
    }

    @Override
    public byte[] getImageFromFileSystem(Long imageId, Long userId) throws IOException {
        Optional<ImageData> fileData = repository.findByImageByIdAndUserId(imageId, userId);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Override
    public ImageData updateImageInFileSystem(String fileName, MultipartFile file) throws IOException {
        Optional<ImageData> fileData = repository.findByName(fileName);
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        Date currentDate = new Date();
        int versionCopy = 0;
        for (int i = 0; i < getTotalImageInFileSystem(); i++) {
            if (file.getOriginalFilename().equalsIgnoreCase(repository.getDuplicateImageName().get(i))) {
                versionCopy++;
            }
        }

        ImageData imageData = fileData.get();
        imageData.setName(file.getOriginalFilename());
        imageData.setType(file.getContentType());
        imageData.setCreatedTime(currentDate);
        imageData.setFilePath(filePath);
        imageData.setVersionCopy(versionCopy);

        repository.save(imageData);

        if (imageData.getVersionCopy() == 0) {
            file.transferTo(new File(FOLDER_PATH + file.getOriginalFilename()));
        } else {
            file.transferTo(new File(FOLDER_PATH + file.getOriginalFilename()
                    .substring(0, file.getOriginalFilename().lastIndexOf('.'))
                    + " " + "(" + imageData.getVersionCopy() + ")" +
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'))));
        }

        if (imageData != null) {
            return imageData;
        }
        return null;
    }

    @Override
    public List<String> getDuplicatedImageInFileSystem() {
        return repository.getDuplicateImageName();
    }

    @Override
    public int getTotalImageInFileSystem() {
        return repository.getTotalImages();
    }


}
