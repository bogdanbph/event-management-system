package org.example.eventsmanagementsystem.model.dto;

import lombok.Builder;
import org.example.eventsmanagementsystem.model.dto.enums.UserRole;

@Builder
public record UserDTO(String username, String password, UserRole role) { }
