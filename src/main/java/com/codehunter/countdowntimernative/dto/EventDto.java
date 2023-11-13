package com.codehunter.countdowntimernative.dto;

import java.time.ZonedDateTime;

public record EventDto(Long id, String name, ZonedDateTime dateTime) {
}
