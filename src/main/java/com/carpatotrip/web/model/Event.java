package com.carpatotrip.web.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events", schema = "carpato_trip_schema")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "event_type")
    private String type;
    
    @Column(name = "event_photo_url")
    private String photoUrl;

    @Column(name = "event_start_time")
    private LocalDateTime startTime;

    @Column(name = "event_end_time")
    private LocalDateTime endTime;
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdOn;
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private TravelClub travelClub;

}
