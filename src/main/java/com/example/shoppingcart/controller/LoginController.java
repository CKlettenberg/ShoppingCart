package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        
        if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            
            return ResponseEntity.ok("{ \"token\": \"mock-token\" }");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
