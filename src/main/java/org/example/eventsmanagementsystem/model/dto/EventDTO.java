package org.example.eventsmanagementsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.eventsmanagementsystem.model.dto.enums.EventType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EventDTO {

    private String title;
    private LocalDate date;
    private Float price;
    private String description;
    private EventType eventType;

    private EventDTO(EventBuilder builder) {
        this.title = builder.title;
        this.date = builder.date;
        this.price = builder.price;
        this.description = builder.description;
        this.eventType = builder.eventType;
    }

    public static class EventBuilder {
        private final String title;
        private final LocalDate date;

        private Float price;
        private String description;
        private EventType eventType;

        public EventBuilder(String title, LocalDate date) {
            this.title = title;
            this.date = date;
        }

        public EventBuilder price(Float price) {
            this.price = price;
            return this;
        }

        public EventBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EventBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public EventDTO build() {
            return new EventDTO(this);
        }
    }
}
