package com.carpatotrip.web.service;

import java.util.List;

import com.carpatotrip.web.dto.TravelClubDto;
import com.carpatotrip.web.model.TravelClub;

public interface TravelClubService {

    List<TravelClubDto> findAllTravelClubs();

    TravelClub saveTravelClub(TravelClubDto travelClubDto);

    TravelClubDto findTravelClubById(Long id);

    void updateTravelClub(TravelClubDto travelClubDto);

    void deleteTravelClub(Long clubId);

    List<TravelClubDto> searchTravelClubs(String query);

}
