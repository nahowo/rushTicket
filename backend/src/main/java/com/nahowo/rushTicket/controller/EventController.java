package com.nahowo.rushTicket.controller;

import com.nahowo.rushTicket.config.result.ResultCode;
import com.nahowo.rushTicket.config.result.ResultResponse;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventUpdateRequest;
import com.nahowo.rushTicket.dto.response.EventDetailResponse;
import com.nahowo.rushTicket.dto.response.EventResponse;
import com.nahowo.rushTicket.dto.response.SeatStatusesResponse;
import com.nahowo.rushTicket.dto.response.SeatStatusesResponse.SeatStatusResponse;
import com.nahowo.rushTicket.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Events")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ResultResponse> viewEvents() {
        List<EventResponse> events = eventService.viewsEvents();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EVENTS_VIEW, events));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ResultResponse> viewEvent(@PathVariable Long eventId) {
        EventDetailResponse event = eventService.viewEvent(eventId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EVENT_VIEW, event));
    }

    @GetMapping("/eventSeats/{eventId}")
    public ResponseEntity<ResultResponse> viewSeatsStatus(@PathVariable Long eventId,
        @RequestParam Long eventDateTimeId) {
        SeatStatusesResponse seatStatuses = eventService.viewSeatStatus(eventId, eventDateTimeId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SEAT_STATUS_VIEW, seatStatuses));
    }

    @PostMapping("/bookSeat")
    public ResponseEntity<ResultResponse> bookSeat(@RequestParam Long userId, @RequestParam Long seatStatusId) {
        SeatStatusResponse seatStatus = eventService.bookSeat(userId, seatStatusId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.SEAT_BOOK, seatStatus));
    }

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

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ResultResponse> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.EVENT_DELETE));
    }
}
