package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    User registerUser(
            String firstName, String lastName, String email, String password, String address, String phoneNumber,
            MultipartFile file) throws IOException;
    //User can register as seller or buyer
    List<User> getUsers();
    String updateUserInformation(
            String firstName, String lastName, String email, String password, String address, String phoneNumber);

    String updateUserAvatar(String email, String fileName, MultipartFile file) throws IOException;

    //If current login user is an administrator, he/she can get a list of all users
    String deleteUser(String email);
    User getUser(String email);
    List<User> getAllUsers();
}
