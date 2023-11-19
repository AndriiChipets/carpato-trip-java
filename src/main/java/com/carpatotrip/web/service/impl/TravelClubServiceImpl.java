package com.carpatotrip.web.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carpatotrip.web.dto.TravelClubDto;
import com.carpatotrip.web.mapper.TravelClubMapper;
import com.carpatotrip.web.model.TravelClub;
import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.repository.TravelClubRepository;
import com.carpatotrip.web.repository.UserEntityRepository;
import com.carpatotrip.web.security.SecurityUtil;
import com.carpatotrip.web.service.TravelClubService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TravelClubServiceImpl implements TravelClubService {

    private final TravelClubRepository travelClubRepository;
    private final UserEntityRepository userEntityRepository;

    @Override
    public List<TravelClubDto> findAllTravelClubs() {
        List<TravelClub> travelClubs = travelClubRepository.findAll();
        return travelClubs.stream()
                .map(TravelClubMapper::mapToTravelClubDto)
                .sorted(Comparator.comparingLong(TravelClubDto::getId))
                .toList();
    }

    @Override
    public TravelClub saveTravelClub(TravelClubDto travelClubDto) {
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userEntityRepository.findByEmail(userEmail);
        TravelClub travelClub = TravelClubMapper.mapToTravelClub(travelClubDto);
        travelClub.setCreatedBy(user);
        return travelClubRepository.save(travelClub);
    }

    @Override
    public TravelClubDto findTravelClubById(Long id) {
        TravelClub travelClub = travelClubRepository.findById(id).get();
        return TravelClubMapper.mapToTravelClubDto(travelClub);
    }
    
    @Override
    public void updateTravelClub(TravelClubDto travelClubDto) {
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userEntityRepository.findByEmail(userEmail);
        TravelClub travelClub = TravelClubMapper.mapToTravelClub(travelClubDto);
        travelClub.setCreatedBy(user);
        travelClubRepository.save(travelClub);
    }
    
    @Override
    public void deleteTravelClub(Long clubId) {
        travelClubRepository.deleteById(clubId);
    }
    
    @Override
    public List<TravelClubDto> searchTravelClubs(String query) {
        List<TravelClub> travelClubs = travelClubRepository.searchTravelClubs(query);
        return travelClubs.stream()
                .map(TravelClubMapper::mapToTravelClubDto)
                .sorted(Comparator.comparingLong(TravelClubDto::getId))
                .toList();
    }
    
}
