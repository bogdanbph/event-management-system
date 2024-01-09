package org.example.eventsmanagementsystem.controller.util;

import lombok.experimental.UtilityClass;
import org.example.eventsmanagementsystem.model.dto.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public final class ResponseBuilderHelper {

    public static ResponseEntity<ResponsePayload> buildResponsePayload(Object response, HttpStatus status) {
        return new ResponseEntity<>(ResponsePayload.builder()
                .response(response)
                .statusCode(status.name())
                .build(), status);
    }
}
