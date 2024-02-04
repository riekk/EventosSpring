package org.viaadamo.spring.service;

import org.springframework.stereotype.Service;
import org.viaadamo.spring.dto.HostDTO;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.repository.HostRepository;

import java.util.List;

@Service
public class HostService {
    private final HostRepository repository;

    public HostService (HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findAll() {
        return repository.findAll();
    }

    public List<HostDTO> getEventsCount() {
        return repository.eventsForHost();
    }

    public Long getEventsStatistics(Long id) {
        return repository.countEventsByHostId(id);
    }

    public List<Host> getByManagerName(String name) {
        return repository.findHostByManager_NameIgnoreCaseContaining(name);
    }

}
