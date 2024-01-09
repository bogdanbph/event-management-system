package org.example.eventsmanagementsystem.model.dto;

import lombok.Builder;

@Builder
public record ResponsePayload(Object response, String statusCode) { }
