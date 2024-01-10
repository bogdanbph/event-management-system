package org.example.eventsmanagementsystem.converter.impl;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.util.PasswordEncoder;
import org.example.eventsmanagementsystem.model.User;
import org.example.eventsmanagementsystem.model.dto.UserDTO;

public class UserConverter implements ObjectConverter<User, UserDTO> {
    @Override
    public User convertSecondToFirst(UserDTO userDTO) {
        User user = new User();

        user.setRole(userDTO.role());
        user.setUsername(userDTO.username());
        user.setPassword(PasswordEncoder.encodeDecodePassword(userDTO.password(), true));
        user.setEmail(user.getEmail());

        return user;
    }

    @Override
    public UserDTO convertFirstToSecond(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
