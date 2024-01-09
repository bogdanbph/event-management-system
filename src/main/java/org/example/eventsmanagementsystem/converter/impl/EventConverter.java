package org.example.eventsmanagementsystem.converter.impl;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.model.Event;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.springframework.stereotype.Component;

public class EventConverter implements ObjectConverter<Event, EventDTO> {

    @Override
    public Event convertSecondToFirst(EventDTO eventDTO) {
        Event event = new Event();

        event.setDate(eventDTO.getDate());
        event.setPrice(eventDTO.getPrice());
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setType(eventDTO.getEventType());

        return event;
    }

    @Override
    public EventDTO convertFirstToSecond(Event event) {

        return new EventDTO.EventBuilder(event.getTitle(), event.getDate())
                .description(event.getDescription())
                .price(event.getPrice())
                .eventType(event.getType())
                .build();
    }
}
