package com.carpatotrip.web.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.carpatotrip.web.model.UserEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class TravelClubDto {

    private Long id;
    @NotEmpty(message = "Travel Club title should not be empty")
    private String title;
    @NotEmpty(message = "Photo link  should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<EventDto> events;

}
