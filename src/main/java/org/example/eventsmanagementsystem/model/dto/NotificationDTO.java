package org.example.eventsmanagementsystem.model.dto;

import lombok.Builder;
import org.example.eventsmanagementsystem.model.dto.enums.NotificationType;

@Builder
public record NotificationDTO (EventDTO event, NotificationType type) { }
