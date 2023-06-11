package com.autenticacion.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.models.GymClass;

public interface GymClassRepository extends JpaRepository<GymClass, Integer> {
    
}
