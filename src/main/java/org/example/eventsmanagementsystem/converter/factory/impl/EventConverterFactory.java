package org.example.eventsmanagementsystem.converter.factory.impl;

import org.example.eventsmanagementsystem.converter.ObjectConverter;
import org.example.eventsmanagementsystem.converter.factory.ObjectConverterFactory;
import org.example.eventsmanagementsystem.converter.impl.EventConverter;
import org.example.eventsmanagementsystem.model.Event;
import org.example.eventsmanagementsystem.model.dto.EventDTO;

public class EventConverterFactory implements ObjectConverterFactory<Event, EventDTO> {
    @Override
    public ObjectConverter<Event, EventDTO> createConverter() {
        return new EventConverter();
    }
}
