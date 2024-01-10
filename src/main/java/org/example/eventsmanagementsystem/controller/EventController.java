package org.example.eventsmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.eventsmanagementsystem.controller.util.ResponseBuilderHelper;
import org.example.eventsmanagementsystem.exception.EventNotFoundException;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.example.eventsmanagementsystem.model.dto.ResponsePayload;
import org.example.eventsmanagementsystem.service.EventService;
import org.example.eventsmanagementsystem.service.proxy.ServiceProxy;
import org.example.eventsmanagementsystem.service.proxy.impl.EventProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final ServiceProxy eventProxy;
    private final EventService eventService;

    public EventController(ServiceProxy eventProxy,
                           EventService eventService) {
        this.eventProxy = eventProxy;
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getEventById(@PathVariable("id") Long id, HttpServletRequest request) {
        try {
            return ResponseBuilderHelper.buildResponsePayload(eventProxy.getEventIfAllowed(request, id),
                    HttpStatus.FOUND);
        } catch (EventNotFoundException e) {
            return ResponseBuilderHelper.buildResponsePayload(String.format("Event with id %d not found!", id),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ResponsePayload> saveEvent(@RequestBody EventDTO eventDTO) {
        try {
            eventService.createEvent(eventDTO);
            return ResponseBuilderHelper.buildResponsePayload("Event created!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseBuilderHelper.buildResponsePayload("Wrong payload...", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload> updateEvent(@PathVariable("id") Long id, @RequestBody EventDTO eventDTO) {
        try {
            return ResponseBuilderHelper.buildResponsePayload(eventService.updateEvent(id, eventDTO), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseBuilderHelper.buildResponsePayload("Wrong payload...", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePayload> deleteEvent(@PathVariable("id") Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseBuilderHelper.buildResponsePayload("Event deleted!", HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseBuilderHelper.buildResponsePayload("Unknown event...", HttpStatus.NOT_FOUND);
        }
    }
}
