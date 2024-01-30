package org.viaadamo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.viaadamo.spring.dto.HostDTO;
import org.viaadamo.spring.entity.Host;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {

    @Query("SELECT new org.viaadamo.spring.dto.HostDTO(h.name, COUNT(e)) FROM Host h JOIN Event e ON e.host.id = h.id GROUP BY h.name")
    List<HostDTO> eventsForHost();

    Long countEventsById(Long id);
    List<Host> findHostByManager_NameIgnoreCaseContaining(String name);

    //Host findHostByManager_Name(String name);
    
}
