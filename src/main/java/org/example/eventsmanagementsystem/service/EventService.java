package org.example.eventsmanagementsystem.service;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.factory.ObjectConverterFactory;
import org.example.eventsmanagementsystem.converter.factory.impl.EventConverterFactory;
import org.example.eventsmanagementsystem.exception.EventNotFoundException;
import org.example.eventsmanagementsystem.model.Event;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.example.eventsmanagementsystem.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ObjectConverter<Event, EventDTO> eventConverter;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

        ObjectConverterFactory<Event, EventDTO> factory = new EventConverterFactory();
        this.eventConverter = factory.createConverter();
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.getEventById(id)
                .orElseThrow(EventNotFoundException::new);
        return eventConverter.convertFirstToSecond(event);
    }

    public void createEvent(EventDTO eventDTO) {
        Event event = eventConverter.convertSecondToFirst(eventDTO);
        eventRepository.save(event);
    }
}
