package com.autenticacion.controllers;

import com.autenticacion.models.ClassSchedule;
import com.autenticacion.repositories.ClassScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClassScheduleController {

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    /**
     * Retrieves a list of all class schedules.
     *
     * @return a list of all class schedules.
     */
    @GetMapping("/classSchedules")
    public List<ClassSchedule> getAllClassSchedules() {
        return classScheduleRepository.findAll();
    }

    /**
     * Adds a new class schedule.
     *
     * @param classSchedule the class schedule to add.
     * @return a confirmation message of the addition.
     */
    @PostMapping("/classSchedules")
    public String addClassSchedule(@RequestBody ClassSchedule classSchedule) {
        classScheduleRepository.save(classSchedule);
        return "Class schedule added successfully!";
    }

    /**
     * Deletes a class schedule by ID.
     *
     * @param id the ID of the class schedule to delete.
     * @return true if the class schedule was deleted, false otherwise.
     */
    @DeleteMapping("/classSchedules/{id}")
    public boolean deleteClassSchedule(@PathVariable int id) {
        if (classScheduleRepository.existsById(id)) {
            classScheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Modifies a class schedule.
     *
     * @param classSchedule the class schedule to modify.
     * @return true if the class schedule was modified, false otherwise.
     */
    @PutMapping("/classSchedules")
    public boolean modifyClassSchedule(@RequestBody ClassSchedule classSchedule) {
        classScheduleRepository.save(classSchedule);
        return true;
    }
}

