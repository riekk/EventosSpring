package org.viaadamo.spring.service;

import org.springframework.stereotype.Service;
import org.viaadamo.spring.entity.Manager;
import org.viaadamo.spring.repository.ManagerRepository;

import java.util.List;

@Service
public class ManagerService {

    private final ManagerRepository repository;

    public ManagerService (ManagerRepository repository) {
        this.repository = repository;
    }

    public List<Manager> findAll() {
        return repository.findAll();
    }
}
