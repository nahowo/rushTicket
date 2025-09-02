package com.nahowo.rushTicket.controller;

import com.nahowo.rushTicket.config.result.ResultCode;
import com.nahowo.rushTicket.config.result.ResultResponse;
import com.nahowo.rushTicket.dto.response.VenueResponse;
import com.nahowo.rushTicket.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Venues")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    @Operation(description = "List Venues(Seller)")
    @GetMapping()
    public ResponseEntity<ResultResponse> getVenues() {
        List<VenueResponse> venues = venueService.getVenues();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.VENUES_VIEW, venues));
    }
}
