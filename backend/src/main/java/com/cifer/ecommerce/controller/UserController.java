package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.model.ImageData;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.service.IImageService;
import com.cifer.ecommerce.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public IUserService userService;

    @PostMapping("/register/new-user")
    public ResponseEntity<User> userRegistration(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("image") MultipartFile file) throws IOException {
        User newUser = userService.registerUser(firstName, lastName, email, password, address, phoneNumber, file);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/update/user-information/{email}")
    public ResponseEntity<String> updateUserInformation(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @PathVariable(value = "email") String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        String updatedUser = userService.updateUserInformation(firstName, lastName, email, password, address, phoneNumber);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/update/user-avatar/{email}")
    public ResponseEntity<String> updateUserAvatar(
            @PathVariable String email,
            @RequestParam("fileName") String fileName,
            @RequestParam("image")MultipartFile file) throws IOException {
        String updatedUser = userService.updateUserAvatar(email, fileName, file);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/user-data/{email}")
    public ResponseEntity<String> deleteUserData(@PathVariable String email) {
        String deletedUser = userService.deleteUser(email);
        return ResponseEntity.ok(deletedUser);
    }
}
