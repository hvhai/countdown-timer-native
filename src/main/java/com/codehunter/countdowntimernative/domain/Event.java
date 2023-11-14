package com.codehunter.countdowntimernative.domain;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
@Builder
public class Event {
    private EventId id;
    private String name;
    private ZonedDateTime date;
    private User host;

    @Value
    public static class EventId {
        Long value;
    }

}

