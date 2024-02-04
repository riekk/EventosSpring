package org.viaadamo.spring.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.viaadamo.spring.dto.PersonalDTO;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.entity.util.PersonalType;
import org.viaadamo.spring.exception.ViaAdamoException;
import org.viaadamo.spring.service.EventService;
import org.viaadamo.spring.controller.util.EventFieldSort;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventRestController implements EventApi {

    private final EventService service;

    public EventRestController(EventService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = service.findAll();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable("id") Long id) {
        Event event = service.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);

    }

    @PostMapping("/new")
    public ResponseEntity<Event> newEvent(@RequestBody Event event) {
        Event newEvent = service.create(event);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @PutMapping("/put/update")
    public ResponseEntity<Event> update(@RequestBody Event event) {
        try {
            Event eventUpdated = service.update(event);
            return new ResponseEntity<>(eventUpdated, HttpStatus.OK);
        } catch (ViaAdamoException e) {
            return new ResponseEntity<>(event, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if (id != 0) {
            service.delete(id);
            return new ResponseEntity<>("The event was deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The id cant be 0.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mostfences")
    public ResponseEntity<Event> getFirstTaskOrderDesc() {
        Event event = service.mostFences();
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

   /* @GetMapping("/date")
    public ResponseEntity<List<Event>> getByDate() {
        List<Event> events = service.findByDate();
        return new ResponseEntity<>(events, HttpStatus.FOUND);
    }
    */
   @GetMapping("/date/{start}/{end}")
   public ResponseEntity<List<Event>> getByDate(@PathVariable("start") @Parameter(description = "Date Format: yyyy-MM-dd", example = "2023-01-01") String start,
                                                @PathVariable("end") @Parameter(description = "Date Format: yyyy-MM-dd", example = "2023-12-31") String end) throws ViaAdamoException {
        List<Event> events = service.findByDateBetween(start, end);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/host/{host_id}")
    public ResponseEntity<List<Event>> getByHostId(@PathVariable("host_id") Long id) {
        List<Event> events = service.findAllByHostId(id);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/e")
    public ResponseEntity<List<Event>> getEventPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "3") Integer pageSize,
                                                    @RequestParam(required = false) EventFieldSort sortBy,
                                                    @RequestParam(defaultValue = "ASC") Sort.Direction orderBy) {
        List<Event> events = service.findAllPageabled(pageNo-1, pageSize, sortBy, orderBy);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{idEvent}/personal/{typePersonal}")
    public ResponseEntity<List<PersonalDTO>> getEventPersonalByType(@PathVariable("idEvent") Long idEvent,@PathVariable("typePersonal") PersonalType typePersonal) {
       List<PersonalDTO> personal = service.getEventPersonalByType(idEvent, typePersonal);
       return new ResponseEntity<>(personal, HttpStatus.OK);
    }
}
