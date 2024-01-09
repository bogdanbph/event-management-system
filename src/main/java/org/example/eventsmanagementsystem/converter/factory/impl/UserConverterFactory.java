package org.example.eventsmanagementsystem.converter.factory.impl;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.factory.ObjectConverterFactory;
import org.example.eventsmanagementsystem.converter.impl.UserConverter;
import org.example.eventsmanagementsystem.model.User;
import org.example.eventsmanagementsystem.model.dto.UserDTO;

public class UserConverterFactory implements ObjectConverterFactory<User, UserDTO> {
    @Override
    public ObjectConverter<User, UserDTO> createConverter() {
        return new UserConverter();
    }
}
