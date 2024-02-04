package org.viaadamo.spring.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.viaadamo.spring.service.EventService;
import org.viaadamo.spring.service.HostService;

import java.time.LocalDate;

@RestController
@RequestMapping("/statistics")
public class StatisticsRestController implements StatisticsApi {

    private final EventService eventService;
    private final HostService hostService;



    public StatisticsRestController(EventService eventService, HostService hostService) {
        this.eventService = eventService;
        this.hostService = hostService;
    }

    @GetMapping("/events/host/{id}")
    public ResponseEntity<Long> getTotalEventsById(@PathVariable("id") Long id) {
        Long total = hostService.getEventsStatistics(id);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @GetMapping("/events/{startDate}/{endDate}")
    public ResponseEntity<Long> countEventsDateBetween(@PathVariable("startDate") @Parameter(description = "Date Format: yyyy-MM-dd", example = "2023-12-01") LocalDate startDate,
                                                       @PathVariable("endDate") @Parameter(description = "Date Format: yyyy-MM-dd", example = "2023-12-31") LocalDate endDate) {
        Long count = eventService.getEventsDateBetween(startDate, endDate);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
