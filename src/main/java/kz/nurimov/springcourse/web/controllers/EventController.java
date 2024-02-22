package kz.nurimov.springcourse.web.controllers;

import jakarta.validation.Valid;
import kz.nurimov.springcourse.web.dto.EventDTO;
import kz.nurimov.springcourse.web.models.Event;
import kz.nurimov.springcourse.web.models.UserEntity;
import kz.nurimov.springcourse.web.repository.UserRepository;
import kz.nurimov.springcourse.web.service.EventService;
import kz.nurimov.springcourse.web.service.UserService;
import kz.nurimov.springcourse.web.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<EventDTO> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId,  Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event")EventDTO eventDTO, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDTO);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        UserEntity user = new UserEntity();
        EventDTO event = eventService.findEventById(eventId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("club",event);
        model.addAttribute("user", user);
        model.addAttribute("event", event);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDTO event = eventService.findEventById(eventId);
        model.addAttribute("event", event);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", eventDTO);
            return "events-edit";
        }

        EventDTO eventInDb = eventService.findEventById(eventId);

        eventDTO.setId(eventId);
        eventDTO.setClub(eventInDb.getClub());
        eventService.updateEvent(eventDTO);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }



}
