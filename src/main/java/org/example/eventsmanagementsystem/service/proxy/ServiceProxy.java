package org.example.eventsmanagementsystem.service.proxy;

import jakarta.servlet.http.HttpServletRequest;
import org.example.eventsmanagementsystem.model.dto.EventDTO;

public interface ServiceProxy {

    EventDTO getEventIfAllowed(HttpServletRequest request, Long eventId);
}
