package org.example.eventsmanagementsystem.controller;

import org.example.eventsmanagementsystem.controller.util.ResponseBuilderHelper;
import org.example.eventsmanagementsystem.model.dto.ResponsePayload;
import org.example.eventsmanagementsystem.model.dto.UserDTO;
import org.example.eventsmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> saveUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseBuilderHelper.buildResponsePayload("User created!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseBuilderHelper.buildResponsePayload("Wrong payload...", HttpStatus.BAD_REQUEST);
        }
    }
}
