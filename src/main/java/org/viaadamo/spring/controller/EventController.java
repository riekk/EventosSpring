package org.viaadamo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.service.EventService;

@Controller
@RequestMapping("/view")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/events")
    public String getEvents(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "event/events";
    }

    @GetMapping("/events/create")
    public String addNewEvent(Model model) {
        model.addAttribute("event", new Event());
        return "event/add";
    }

    @PostMapping("/add")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.create(event);
        return "redirect:/view/events";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);

        return "event/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        eventService.delete(id);
        return "redirect:/view/events";
    }
}
