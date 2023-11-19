package com.carpatotrip.web.mapper;

import com.carpatotrip.web.dto.EventDto;
import com.carpatotrip.web.model.Event;

public class EventMapper {
    
    public static Event mapToEvent (EventDto eventDto) {
        return Event.builder()
                .withId(eventDto.getId())
                .withName(eventDto.getName())
                .withType(eventDto.getType())
                .withPhotoUrl(eventDto.getPhotoUrl())
                .withStartTime(eventDto.getStartTime())
                .withEndTime(eventDto.getEndTime())
                .withCreatedOn(eventDto.getCreatedOn())
                .withUpdatedOn(eventDto.getUpdatedOn())
                .withTravelClub(eventDto.getTravelClub())
                .build();
    }
    
    public static EventDto mapToEventDto (Event event) {
        return EventDto.builder()
                .withId(event.getId())
                .withName(event.getName())
                .withType(event.getType())
                .withPhotoUrl(event.getPhotoUrl())
                .withStartTime(event.getStartTime())
                .withEndTime(event.getEndTime())
                .withCreatedOn(event.getCreatedOn())
                .withUpdatedOn(event.getUpdatedOn())
                .withTravelClub(event.getTravelClub())
                .build();
    }

}
