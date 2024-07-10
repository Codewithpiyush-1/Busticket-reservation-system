package com.reservationapp.Controller;

import com.reservationapp.Entity.UserRegistration;

import com.reservationapp.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {
    @Autowired
    private UserServiceImpl userService;

@PostMapping
    public String createUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("profilePicture") MultipartFile profilePicture
            //multipartFile converts 0s and 1s
    ) {
//We create a new UserRegistration object to represent the user.
//Think of it as a blank form where we’ll fill in the user’s details.
//Setting User Properties:
//We use the setName, setEmail, and setPassword methods to fill in the form:
//name: The user’s name.
//email: The user’s email address.
//password: The user’s chosen password.
        try {
            UserRegistration userRegistration = new UserRegistration();
            userRegistration.setName(name);
            userRegistration.setEmail(email);
            userRegistration.setPassword(password);
            userRegistration.setProfilePicture(profilePicture.getBytes());

            userService.createUser(userRegistration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Done";

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRegistration> getUserbyId(@PathVariable long id){
        UserRegistration user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
