package com.cifer.ecommerce.service;

import com.cifer.ecommerce.exception.UserAlreadyExistException;
import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.model.Role;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.RoleRepository;
import com.cifer.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IImageService imageService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String address, String phoneNumber,
                             MultipartFile file) throws IOException {
        User registeredUser = new User();
        Optional<List<String>> allExistedEmail = userRepository.getAllEmail();
        for (int i = 0; i < allExistedEmail.get().size(); i++) {
            if (email.equalsIgnoreCase(allExistedEmail.get().get(i))) {
                throw new UserAlreadyExistException(email + " already exists");
            }
        }
        registeredUser.setFirstName(firstName);
        registeredUser.setLastName(lastName);
        registeredUser.setEmail(email);
        registeredUser.setPassword(passwordEncoder.encode(password));
        Role userDefaultRole = roleRepository.findByName("ROLE_USER").get();
        registeredUser.setRoles(Collections.singleton(userDefaultRole));
        registeredUser.setAddress(address);
        registeredUser.setPhoneNumber(phoneNumber);
        String imageType = "User Avatar";
        ImageData data = imageService.uploadImageToFileSystem(file, imageType);
        registeredUser.setData(data);
        return userRepository.save(registeredUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public String updateUserInformation(String firstName, String lastName, String email, String password, String address,  String phoneNumber) {
        Optional<User> updatedUser = userRepository.findByEmail(email);
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

            userRepository.save(user); // Save the updated user
            return "Update successfully";
        }
        return "Unsuccessful, maybe the email does not exist";
    }

    @Override
    public String updateUserAvatar(String email, String fileName, MultipartFile file) throws IOException {
        Optional<User> updatedUser = userRepository.findByEmail(email);
        ImageData imageData = imageService.updateImageInFileSystem(fileName, file);
        User newInfoUser = updatedUser.get();
        newInfoUser.setData(imageData);
        userRepository.save(newInfoUser);
        return "Update successfully";
    }

    @Override
    public String deleteUser(String email) {
        Optional<User> deletedUser = userRepository.findByEmail(email);
        if (deletedUser.isPresent()) {
            userRepository.deleteByEmail(email);
            return "Delete successfully";
        }
        return "Nothing happened, maybe we cannot find your email";
    }

    @Override
    public User getUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email).get();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
