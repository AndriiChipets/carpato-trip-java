package com.carpatotrip.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carpatotrip.web.dto.TravelClubDto;
import com.carpatotrip.web.model.TravelClub;
import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.security.SecurityUtil;
import com.carpatotrip.web.service.TravelClubService;
import com.carpatotrip.web.service.UserEntityService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TravelClubController {

    private final TravelClubService travelClubService;
    private final UserEntityService userEntityService;
    

    @GetMapping("/clubs")
    public String getAllTravelClubs(Model model) {
        UserEntity user = new UserEntity();
        List<TravelClubDto> travelClubs = travelClubService.findAllTravelClubs();
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            user = userEntityService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", travelClubs);
        return "clubs-list";
    }
    
    @GetMapping("/clubs/{clubId}")
    public String travelClubDetail(@PathVariable("clubId") Long clubId, Model model) {
        UserEntity user = new UserEntity();
        TravelClubDto travelClubDto = travelClubService.findTravelClubById(clubId);
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            user = userEntityService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", travelClubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/new")
    public String createTravelClubForm(Model model) {
        TravelClub travelClub = new TravelClub();
        model.addAttribute("club", travelClub);
        return "clubs-create";
    }
    
    @GetMapping("/clubs/{clubId}/delete")
    
    public String deleteTravelClub(@PathVariable("clubId") Long clubId) {
        travelClubService.deleteTravelClub(clubId);
        return "redirect:/clubs";
    }
    
    @GetMapping("/clubs/search")
    public String searchTravelClub(@RequestParam(value = "query") String query, Model model) {
        List<TravelClubDto> travelClubs = travelClubService.searchTravelClubs(query);
        model.addAttribute("clubs", travelClubs);
        return "clubs-list";
    }
   
    @PostMapping("/clubs/new")
    public String saveTravelClub(
            @Valid @ModelAttribute("club") TravelClubDto travelClubDto, 
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", travelClubDto);
            return "clubs-create";
        }
        travelClubService.saveTravelClub(travelClubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editTravelClubForm(@PathVariable("clubId") Long clubId, Model model) {
        TravelClubDto travelClubDto = travelClubService.findTravelClubById(clubId);
        model.addAttribute("club", travelClubDto);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateTravelClub(
            @PathVariable("clubId") Long clubId,
            @Valid @ModelAttribute("club") TravelClubDto travelClubDto, 
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("club", travelClubDto);
            return "clubs-edit";
        }
        travelClubDto.setId(clubId);
        travelClubService.updateTravelClub(travelClubDto);
        return "redirect:/clubs";
    }

}
