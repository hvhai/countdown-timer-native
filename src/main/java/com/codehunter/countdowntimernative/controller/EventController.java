package com.codehunter.countdowntimernative.controller;

import com.codehunter.countdowntimernative.business.EventService;
import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.domain.User;
import com.codehunter.countdowntimernative.dto.CreateEventDto;
import com.codehunter.countdowntimernative.dto.EventDto;
import com.codehunter.countdowntimernative.mapper.EventMapper;
import com.codehunter.countdowntimernative.response.ResponseDTO;
import com.codehunter.countdowntimernative.response.ResponseFormatter;
import com.codehunter.countdowntimernative.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.codehunter.countdowntimernative.mapper.EventMapper.toEvent;


@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<EventDto>>> getAllEventsByUser() {
        User user = AuthenticationUtil.getUser();
        log.info("getAllEventsByUser with userID: {}", user.getId());
        List<EventDto> events = eventService.getAllEventByUser(user).stream().map(EventMapper::toEvent).toList();
        return ResponseFormatter.handleList(events);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO<EventDto>> creatEvent(@RequestBody CreateEventDto createEventDto) {
        User user = AuthenticationUtil.getUser();
        log.info("creatEvent with userID: {}", user.getId());
        Event event = Event.builder().name(createEventDto.name())
                .date(createEventDto.dateTime())
                .host(user)
                .build();
        Event newEvent = eventService.createEvent(event);
        return ResponseFormatter.handleSingle(toEvent(newEvent), new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        log.info("deleteEvent with userID: {}", id);
        eventService.delete(id);
    }

}
