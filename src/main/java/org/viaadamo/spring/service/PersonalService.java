package org.viaadamo.spring.service;

import org.springframework.stereotype.Service;
import org.viaadamo.spring.entity.Personal;
import org.viaadamo.spring.repository.PersonalRepository;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository repository;

    public PersonalService (PersonalRepository repository) {
        this.repository = repository;
    }
    public List<Personal> findAll() {
        return repository.findAll();
    }
}
