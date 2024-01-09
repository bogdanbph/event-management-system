package org.example.eventsmanagementsystem.service.proxy.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.example.eventsmanagementsystem.exception.UserNotAllowedException;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.example.eventsmanagementsystem.model.dto.UserDTO;
import org.example.eventsmanagementsystem.model.dto.enums.UserRole;
import org.example.eventsmanagementsystem.service.EventService;
import org.example.eventsmanagementsystem.service.proxy.ServiceProxy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventProxy implements ServiceProxy {

    private static final List<UserRole> ALLOWED_ROLES = List.of(UserRole.ADMIN, UserRole.ORGANIZER);

    private final EventService eventService;

    public EventProxy(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public EventDTO getEventIfAllowed(HttpServletRequest servletRequest, Long id) {
        UserDTO userDTO = (UserDTO) servletRequest.getAttribute("user");

        if (ALLOWED_ROLES.contains(userDTO.role())) {
            return eventService.getEventById(id);
        } else {
            throw new UserNotAllowedException("User not allowed to retrieve events...");
        }
    }
}
