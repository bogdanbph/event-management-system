package org.example.eventsmanagementsystem.service;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.factory.ObjectConverterFactory;
import org.example.eventsmanagementsystem.converter.factory.impl.EventConverterFactory;
import org.example.eventsmanagementsystem.exception.EventNotFoundException;
import org.example.eventsmanagementsystem.model.Event;
import org.example.eventsmanagementsystem.model.dto.EventDTO;
import org.example.eventsmanagementsystem.model.dto.NotificationDTO;
import org.example.eventsmanagementsystem.model.dto.enums.NotificationType;
import org.example.eventsmanagementsystem.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ObjectConverter<Event, EventDTO> eventConverter;
    private final NotificationService notificationService;

    public EventService(EventRepository eventRepository, NotificationService notificationService) {
        this.eventRepository = eventRepository;
        this.notificationService = notificationService;

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

        var notification = NotificationDTO.builder()
                .event(eventDTO)
                .type(NotificationType.NEW_EVENT)
                .build();

        notificationService.notify(notification);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Optional<Event> optionalEvent = eventRepository.getEventById(id);

        boolean isModified = false;
        Event event = new Event();

        if (optionalEvent.isPresent()) {
            isModified = true;
            event = optionalEvent.get();
        }

        if (eventDTO.getEventType() != null) {
            event.setType(eventDTO.getEventType());
        }

        if (eventDTO.getTitle() != null) {
            event.setTitle(eventDTO.getTitle());
        }

        if (eventDTO.getDate() != null) {
            event.setDate(eventDTO.getDate());
        }

        if (eventDTO.getDescription() != null) {
            event.setDescription(eventDTO.getDescription());
        }

        if (eventDTO.getPrice() != null) {
            event.setPrice(eventDTO.getPrice());
        }

        eventRepository.save(event);

        EventDTO newEventDTO = eventConverter.convertFirstToSecond(event);
        var notificationDTOBuilder = NotificationDTO.builder().event(newEventDTO);

        if (isModified) {
            notificationDTOBuilder.type(NotificationType.EVENT_MODIFIED);
        } else {
            notificationDTOBuilder.type(NotificationType.NEW_EVENT);
        }

        notificationService.notify(notificationDTOBuilder.build());
        return newEventDTO;
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.getEventById(id)
                .orElseThrow(EventNotFoundException::new);

        EventDTO newEventDTO = eventConverter.convertFirstToSecond(event);
        eventRepository.delete(event);

        var notification =
                NotificationDTO.builder()
                        .event(newEventDTO)
                        .type(NotificationType.EVENT_CANCELLED)
                        .build();

        notificationService.notify(notification);
    }
}
