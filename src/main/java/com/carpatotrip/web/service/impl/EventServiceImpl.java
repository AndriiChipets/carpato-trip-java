package com.carpatotrip.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carpatotrip.web.dto.EventDto;
import com.carpatotrip.web.mapper.EventMapper;
import com.carpatotrip.web.model.Event;
import com.carpatotrip.web.model.TravelClub;
import com.carpatotrip.web.repository.EventRepository;
import com.carpatotrip.web.repository.TravelClubRepository;
import com.carpatotrip.web.service.EventService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final TravelClubRepository travelClubRepository;

    @Override
    public void saveEvent(Long clubId, EventDto eventDto) {
        TravelClub travelClub = travelClubRepository.findById(clubId).get();
        Event event = EventMapper.mapToEvent(eventDto);
        event.setTravelClub(travelClub);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).toList();
    }

    @Override
    public EventDto findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
