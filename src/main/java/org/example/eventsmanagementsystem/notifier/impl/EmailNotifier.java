package org.example.eventsmanagementsystem.notifier.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.example.eventsmanagementsystem.model.dto.NotificationDTO;
import org.example.eventsmanagementsystem.model.dto.enums.NotificationType;
import org.example.eventsmanagementsystem.notifier.EventListener;

@Slf4j
public class EmailNotifier implements EventListener {
    private final String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void update(NotificationDTO notification) {
        EventDTO event = notification.event();
        NotificationType type = notification.type();

        switch (type) {
            case NEW_EVENT ->
                    log.info("Sending email to " + email +
                            " because a new event called " + event.getTitle() + " has been created!");
            case EVENT_CANCELLED ->
                    log.info("Sending email to " + email +
                            " because the event called " + event.getTitle() + " has been cancelled!");
            case EVENT_MODIFIED ->
                    log.info("Sending email to " + email +
                            " because the event called " + event.getTitle() + " has been modified to " + event);
        }
    }
}
