package com.carpatotrip.web.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Entity
@Table(name = "clubs", schema = "carpato_trip_schema")
public class TravelClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "club_title")
    private String title;

    @Column(name = "club_photo_url")
    private String photoUrl;

    @Column(name = "club_content")
    private String content;

    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdOn;
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime updatedOn;
    
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "travelClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();

}
