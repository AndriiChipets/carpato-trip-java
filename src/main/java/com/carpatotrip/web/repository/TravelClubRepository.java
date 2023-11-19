package com.carpatotrip.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carpatotrip.web.model.TravelClub;

@Repository
public interface TravelClubRepository extends JpaRepository<TravelClub, Long> {

    Optional<TravelClub> findByTitle(String title);

    @Query("SELECT c from TravelClub c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<TravelClub> searchTravelClubs(String query);

}
