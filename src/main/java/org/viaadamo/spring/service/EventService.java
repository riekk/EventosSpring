package org.viaadamo.spring.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.viaadamo.spring.dto.PersonalDTO;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.entity.util.PersonalType;
import org.viaadamo.spring.exception.ErrorCode;
import org.viaadamo.spring.exception.ViaAdamoException;
import org.viaadamo.spring.repository.EventRepository;
import org.viaadamo.spring.controller.util.EventFieldSort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;

     public EventService (EventRepository repository) {
         this.repository = repository;
     }

     public List<Event> findAll() {
         return repository.findAll();
     }

     public Event findById(Long id) {
         Optional<Event> optional = repository.findById(id);
         return optional.orElse(null);
     }

     public Event create(Event event) {
         return repository.save(event);
     }

     public void delete(Long id) {
         if (id != 0) {
             repository.deleteById(id);
         }
     }

     public Event update(Event event) throws ViaAdamoException {
         if (event.getId() != null && event.getId() != 0) {
             return repository.save(event);
         }
         throw new ViaAdamoException(ErrorCode.ID_NOTFOUND, "The field ID is mandatory");
     }

     public List<Event> findAllPageabled(Integer pageNo, Integer pageSize, EventFieldSort sortBy, Sort.Direction orderBy) {
         Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orderBy, sortBy.getField()));
         return repository.findAll(pageable).getContent();
     }

     public Event mostFences() {
         return repository.findFirstByOrderByFencesDesc();
     }

     public List<Event> findAllByHostId(Long id) {
         return repository.findAllByHost_Id(id);
     }

     /*public List<Event> findByDate(LocalDateTime date) {
         return repository.findByDate(date);
     }

      */
     public List<Event> findByDateBetween(String start, String end) throws ViaAdamoException {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         try {
             return repository.findAllByDateBetween(LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));
             //return repository.findAllByDateBetween(start, end);
         } catch (DateTimeParseException e) {
             System.out.println("The string is not a date and time: " + e.getMessage());
             throw  new ViaAdamoException(ErrorCode.DATE_FORMAT_ERROR, "Date format not valid.");
         }

     }

     public List<PersonalDTO> getEventPersonalByType(Long idEvent, PersonalType typePersonal) {
         return repository.getPersonalByType(idEvent, typePersonal);
     }

     public Long getEventsDateBetween(LocalDate startDate, LocalDate endDate) {
         return repository.getNumberOfEventsDateBetween(startDate, endDate);
     }

}
