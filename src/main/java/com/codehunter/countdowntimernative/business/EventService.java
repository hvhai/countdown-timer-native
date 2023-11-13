package com.codehunter.countdowntimernative.business;

import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.domain.User;
import com.codehunter.countdowntimernative.jpa.JpaEvent;
import com.codehunter.countdowntimernative.jpa.JpaUser;
import com.codehunter.countdowntimernative.repository.EventRepository;
import com.codehunter.countdowntimernative.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Event createEvent(Event event, User user) {
        if (user == null) {
            log.warn("Cannot create event - empty user");
            return null;
        }

        Optional<JpaUser> existedUser = userRepository.findById(user.getId());
        if (existedUser.isPresent()) {
            JpaEvent jpaEvent = new JpaEvent();
            jpaEvent.setName(event.getName());
            jpaEvent.setPublicTime(event.getDate().toInstant());
            jpaEvent.setUser(existedUser.get());
            JpaEvent newJpaEvent = eventRepository.save(jpaEvent);
            return toEvent(newJpaEvent);
        }
        User jpaUser = userService.createUser(user);

    }

    public Event createEvent(Event event) {
        JpaEvent jpaEvent = new JpaEvent();
        jpaEvent.setName(event.getName());
        jpaEvent.setPublicTime(event.getDate().toInstant());
        JpaEvent newJpaEvent = eventRepository.save(jpaEvent);

        return toEvent(newJpaEvent);
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

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
