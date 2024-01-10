package org.example.eventsmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.eventsmanagementsystem.controller.util.ResponseBuilderHelper;
import org.example.eventsmanagementsystem.model.dto.ResponsePayload;
import org.example.eventsmanagementsystem.model.dto.UserDTO;
import org.example.eventsmanagementsystem.model.dto.enums.NotificationType;
import org.example.eventsmanagementsystem.notifier.impl.EmailNotifier;
import org.example.eventsmanagementsystem.service.NotificationService;
import org.example.eventsmanagementsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;

    public UserController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
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

    @PostMapping("/newsletter")
    public ResponseEntity<ResponsePayload> subscribeToNewsletter(@RequestParam String type, HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getAttribute("user");
        notificationService.subscribe(NotificationType.valueOf(type), new EmailNotifier(user.email()));

        return ResponseBuilderHelper.buildResponsePayload("You have subscribed to " + type + ".", HttpStatus.CREATED);
    }

    @DeleteMapping("/newsletter")
    public ResponseEntity<ResponsePayload> unsubscribeToNewsletter(@RequestParam String type, HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getAttribute("user");
        notificationService.unsubscribe(NotificationType.valueOf(type), new EmailNotifier(user.email()));

        return ResponseBuilderHelper.buildResponsePayload("You have unsubscribed successfully from " + type + ".", HttpStatus.OK);
    }
}
