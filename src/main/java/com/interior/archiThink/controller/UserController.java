package com.interior.archiThink.controller;

import com.interior.archiThink.dto.UserDto;
import com.interior.archiThink.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDTO) {
        UserDto registered = userServiceImpl.register(userDTO);
        if(Objects.nonNull(registered)) {
            return ResponseEntity.ok(registered);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDTO) {
        String token = userServiceImpl.verify(userDTO);
        if(Objects.nonNull(token)) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.notFound().build();
    }
}
