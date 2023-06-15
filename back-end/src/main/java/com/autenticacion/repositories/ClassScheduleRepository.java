package com.autenticacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autenticacion.models.ClassSchedule;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule,Integer>{
    
}
