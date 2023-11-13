package com.codehunter.countdowntimernative.dto;

import java.time.ZonedDateTime;

public record CreateEventDto(String name, ZonedDateTime dateTime) {
}
