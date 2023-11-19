package com.carpatotrip.web.service;

import java.util.List;

import com.carpatotrip.web.dto.EventDto;

public interface EventService {

    void saveEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(Long eventId);

}
