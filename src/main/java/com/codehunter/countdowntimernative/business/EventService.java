package com.codehunter.countdowntimernative.business;

import com.codehunter.countdowntimernative.domain.Event;
import com.codehunter.countdowntimernative.jpa.JpaEvent;
import com.codehunter.countdowntimernative.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

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
