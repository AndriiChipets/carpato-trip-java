package com.carpatotrip.web.mapper;

import java.util.stream.Collectors;

import com.carpatotrip.web.dto.TravelClubDto;
import com.carpatotrip.web.model.TravelClub;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TravelClubMapper {
    
    
    public static TravelClubDto mapToTravelClubDto(TravelClub travelclub) {
        return TravelClubDto.builder()
                .withId(travelclub.getId())
                .withTitle(travelclub.getTitle())
                .withPhotoUrl(travelclub.getPhotoUrl())
                .withContent(travelclub.getContent())
                .withCreatedBy(travelclub.getCreatedBy())
                .withCreatedOn(travelclub.getCreatedOn())
                .withUpdatedOn(travelclub.getUpdatedOn())
                .withEvents(travelclub.getEvents().stream().map(EventMapper::mapToEventDto).collect(Collectors.toSet()))
                .build();
    }
    
    public static TravelClub mapToTravelClub(TravelClubDto travelclubDto) {

        return TravelClub.builder()
                .withId(travelclubDto.getId())
                .withTitle(travelclubDto.getTitle())
                .withPhotoUrl(travelclubDto.getPhotoUrl())
                .withContent(travelclubDto.getContent())
                .withCreatedBy(travelclubDto.getCreatedBy())
                .withCreatedOn(travelclubDto.getCreatedOn())
                .withUpdatedOn(travelclubDto.getUpdatedOn())
                .build();
    }

}
