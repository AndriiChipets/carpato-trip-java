package com.carpatotrip.web.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.carpatotrip.web.model.TravelClub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class EventDto {

    private Long id;
    private String name;
    private String type;
    private String photoUrl;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private TravelClub travelClub;

}
