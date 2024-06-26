package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    public final IUserService userService;

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
    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    
    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }
}
