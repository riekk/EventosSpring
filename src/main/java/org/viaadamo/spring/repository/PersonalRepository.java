package org.viaadamo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.viaadamo.spring.entity.Personal;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {

}
