package org.viaadamo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.viaadamo.spring.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
