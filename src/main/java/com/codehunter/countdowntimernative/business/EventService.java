package com.codehunter.countdowntimernative.business;

import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.domain.User;
import com.codehunter.countdowntimernative.jpa.JpaEvent;
import com.codehunter.countdowntimernative.jpa.JpaUser;
import com.codehunter.countdowntimernative.jpa_repository.EventRepository;
import com.codehunter.countdowntimernative.jpa_repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Event createEvent(Event event) {
        if (event.getHost() == null) {
            log.warn("Cannot create event - empty user");
            return null;
        }

        JpaUser existedUser = userRepository.findById(event.getHost().getId())
                .orElse(createNewUser(event.getHost()));

        JpaEvent jpaEvent = new JpaEvent();
        jpaEvent.setName(event.getName());
        jpaEvent.setPublicTime(event.getDate().toInstant());
        jpaEvent.setUser(existedUser);
        JpaEvent newJpaEvent = eventRepository.save(jpaEvent);
        return toEvent(newJpaEvent);
    }

    private JpaUser createNewUser(User user) {
        JpaUser jpaUser = new JpaUser();
        jpaUser.setId(user.getId());
        jpaUser.setName(user.getName());
        jpaUser.setEventList(Collections.emptyList());
        return userRepository.save(jpaUser);
    }

    private static Event toEvent(JpaEvent newJpaEvent) {
        return Event.builder()
                .id(new Event.EventId(newJpaEvent.getId()))
                .name(newJpaEvent.getName())
                .date(newJpaEvent.getPublicTime().atZone(ZoneOffset.UTC))
                .build();
    }

    public List<Event> getAllEvent() {
        List<JpaEvent> allJpaEvent = eventRepository.findAll();
        return allJpaEvent.stream().map(EventService::toEvent).toList();
    }

    public List<Event> getAllEventByUser(User user) {
        List<JpaEvent> allJpaEvent = eventRepository.findByUserId(user.getId());
        return allJpaEvent.stream().map(EventService::toEvent).toList();
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
