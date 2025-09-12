package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Event;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;

@Getter
public class EventResponse {
    Map<Long, Map<Long, BigDecimal>> prices;

    public EventResponse(Map<Long, Map<Long, BigDecimal>> prices) {
        this.prices = prices;
    }
}
