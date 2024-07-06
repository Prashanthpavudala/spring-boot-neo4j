package com.prashanth.spring_boot_neo4j.controllers;

import com.prashanth.spring_boot_neo4j.requests.CreateUserRequest;
import com.prashanth.spring_boot_neo4j.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    ResponseEntity<String> loggedInUserDetails(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @PostMapping("/register")
    ResponseEntity<?> registerNewUser(@RequestBody CreateUserRequest request) {
        return userService.registerNewUser(request);
    }

}
