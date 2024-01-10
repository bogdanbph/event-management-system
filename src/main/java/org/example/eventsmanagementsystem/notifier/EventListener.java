package org.example.eventsmanagementsystem.notifier;

import org.example.eventsmanagementsystem.model.dto.NotificationDTO;

public interface EventListener {
    void update(NotificationDTO notification);
}
