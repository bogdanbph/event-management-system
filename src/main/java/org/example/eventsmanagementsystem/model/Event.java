package org.example.eventsmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.eventsmanagementsystem.model.dto.enums.EventType;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate date;
    private Float price;
    private String description;

    @Enumerated(EnumType.STRING)
    private EventType type;
}
