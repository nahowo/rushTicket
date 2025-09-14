package com.nahowo.rushTicket.controller;

import com.nahowo.rushTicket.config.result.ResultCode;
import com.nahowo.rushTicket.config.result.ResultResponse;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventUpdateRequest;
import com.nahowo.rushTicket.dto.response.EventResponse;
import com.nahowo.rushTicket.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Events")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ResultResponse> createEvent(
        @RequestBody @Valid EventCreateRequest request) {
        EventResponse event = eventService.createEvent(request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EVENT_CREATE, event));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<ResultResponse> updateEvent(@PathVariable Long eventId,
        @RequestBody @Valid EventUpdateRequest request) {
        EventResponse event = eventService.updateEvent(eventId, request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EVENT_UPDATE, event));
    }
}
