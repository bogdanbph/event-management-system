package org.example.eventsmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.eventsmanagementsystem.model.dto.enums.UserRole;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
