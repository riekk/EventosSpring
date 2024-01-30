package org.viaadamo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.viaadamo.spring.dto.PersonalDTO;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.entity.Personal;
import org.viaadamo.spring.entity.util.PersonalType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


    Event findFirstByOrderByFencesDesc();
    List<Event> findAllByHost_Id(Long id);
    List<Event> findByDate(LocalDateTime date);     //Ajustar para elegir fechas
    List<Event> findAllByDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT new org.viaadamo.spring.dto.PersonalDTO(p.name, p.type, e.name) " +
            "FROM Event e JOIN Personal p ON e.id = p.id " +
            "WHERE p.type = :typePersonal AND e.id = :idEvent")
    List<PersonalDTO> getPersonalByType(Long idEvent, PersonalType typePersonal);
}
