package org.example.eventsmanagementsystem.service;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.factory.ObjectConverterFactory;
import org.example.eventsmanagementsystem.converter.factory.impl.UserConverterFactory;
import org.example.eventsmanagementsystem.exception.EventNotFoundException;
import org.example.eventsmanagementsystem.exception.UserNotFoundException;
import org.example.eventsmanagementsystem.model.User;
import org.example.eventsmanagementsystem.model.dto.UserDTO;
import org.example.eventsmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectConverter<User, UserDTO> userConverter;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        ObjectConverterFactory<User, UserDTO> factory = new UserConverterFactory();
        this.userConverter = factory.createConverter();
    }


    public UserDTO getUserById(Long id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return userConverter.convertFirstToSecond(user);
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return userConverter.convertFirstToSecond(user);
    }

    public void createUser(UserDTO userDTO) {
        User user = userConverter.convertSecondToFirst(userDTO);
        userRepository.save(user);
    }
}
