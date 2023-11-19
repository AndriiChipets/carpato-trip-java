package com.carpatotrip.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.carpatotrip.web.dto.EventDto;
import com.carpatotrip.web.model.Event;
import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.security.SecurityUtil;
import com.carpatotrip.web.service.EventService;
import com.carpatotrip.web.service.UserEntityService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserEntityService userEntityService;
    
    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<EventDto> events = eventService.findAllEvents();
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            userEntityService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "events-list";
    }
    
    @GetMapping("events/{eventId}")
    public String eventDetail(@PathVariable("eventId") Long eventId, Model model) {
        UserEntity user = new UserEntity();
        EventDto eventDto = eventService.findEventById(eventId);
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            userEntityService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{clubId}/new")
    public String createTravelClubForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }
    
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }
    
    @PostMapping("/events/{eventId}/edit")
    public String updateTravelClub(
            @PathVariable("eventId") Long eventId,
            @Valid @ModelAttribute("event") EventDto eventDto, 
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto eventDtoDb = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setTravelClub(eventDtoDb.getTravelClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @PostMapping("/events/{clubId}/new")
    public String saveEvent(
            @PathVariable("clubId") Long clubId,
            @Valid @ModelAttribute("event") EventDto eventDto, 
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "event-create";
        }
        eventService.saveEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }
    
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
    
}
