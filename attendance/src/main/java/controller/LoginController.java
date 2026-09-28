package com.classroom.attendance.controller;

import com.classroom.attendance.entity.User;
import com.classroom.attendance.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody User user) {

        boolean validLogin =
                userService.login(
                        user.getUsername(),
                        user.getPassword()
                );

        if (validLogin) {

            return ResponseEntity.ok(
                    "Login successful"
            );
        }

        return ResponseEntity
                .status(401)
                .body("Invalid username or password");
    }
}