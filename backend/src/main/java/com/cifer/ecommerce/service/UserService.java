package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final IImageService imageService;

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String address, String phoneNumber,
                             MultipartFile file) throws IOException {
        User registeredUser = new User();
        Optional<List<String>> allExistedEmail = repository.getAllEmail();
        for (int i = 0; i < allExistedEmail.get().size(); i++) {
            if (email.equalsIgnoreCase(allExistedEmail.get().get(i))) {
                return null;
            }
        }
        registeredUser.setFirstName(firstName);
        registeredUser.setLastName(lastName);
        registeredUser.setEmail(email);
        registeredUser.setPassword(password);
        registeredUser.setAddress(address);
        registeredUser.setPhoneNumber(phoneNumber);
        String imageType = "User Avatar";
        ImageData data = imageService.uploadImageToFileSystem(file, imageType);
        registeredUser.setData(data);
        return repository.save(registeredUser);
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public String updateUserInformation(String firstName, String lastName, String email, String password, String address,  String phoneNumber) {
        Optional<User> updatedUser = repository.findByEmail(email);
        if(updatedUser.isPresent()) {
            User user = updatedUser.get(); // Retrieve the existing user

            // Update the user's attributes if the corresponding parameter is not null
            if (!Objects.equals(firstName, "")) {
                user.setFirstName(firstName);
            }
            if (!Objects.equals(lastName, "")) {
                user.setLastName(lastName);
            }
            if (!Objects.equals(password, "")) {
                user.setPassword(password);
            }
            if (!Objects.equals(address, "")) {
                user.setAddress(address);
            }
            if (!Objects.equals(phoneNumber, "")) {
                user.setPhoneNumber(phoneNumber);
            }

            repository.save(user); // Save the updated user
            return "Update successfully";
        }
        return "Unsuccessful, maybe the email does not exist";
    }

    @Override
    public String updateUserAvatar(String email, String fileName, MultipartFile file) throws IOException {
        Optional<User> updatedUser = repository.findByEmail(email);
        ImageData imageData = imageService.updateImageInFileSystem(fileName, file);
        User newInfoUser = updatedUser.get();
        newInfoUser.setData(imageData);
        repository.save(newInfoUser);
        return "Update successfully";
    }

    @Override
    public String deleteUser(String email) {
        Optional<User> deletedUser = repository.findByEmail(email);
        if (deletedUser.isPresent()) {
            repository.deleteByEmail(email);
            return "Delete successfully";
        }
        return "Nothing happened, maybe we cannot find your email";
    }

    @Override
    public User getUser(String email) {
        if (repository.findByEmail(email).isPresent()) {
            return repository.findByEmail(email).get();
        }
        return null;
    }
}
