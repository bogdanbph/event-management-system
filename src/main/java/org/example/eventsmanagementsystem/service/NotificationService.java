package org.example.eventsmanagementsystem.service;

import org.example.eventsmanagementsystem.model.dto.NotificationDTO;
import org.example.eventsmanagementsystem.model.dto.enums.NotificationType;
import org.example.eventsmanagementsystem.notifier.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {

    private final Map<NotificationType, List<EventListener>> users;

    public NotificationService() {
        this.users = new HashMap<>();
        Arrays.stream(NotificationType.values())
                .forEach(notification -> users.put(notification, new ArrayList<>()));
    }

    public void subscribe(NotificationType notificationType, EventListener eventListener) {
        users.get(notificationType).add(eventListener);
    }

    public void unsubscribe(NotificationType notificationType, EventListener eventListener) {
        users.get(notificationType).remove(eventListener);
    }

    public void notify(NotificationDTO notification) {
        users.get(notification.type()).forEach(listener -> listener.update(notification));
    }
}
