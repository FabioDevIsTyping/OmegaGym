package com.autenticacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.models.GymClass;
import com.autenticacion.repositories.GymClassRepository;

@RestController
@CrossOrigin
public class GymClassController {
    @Autowired
    private GymClassRepository gymClassRepository;



    /**
     * Retrieves a list of all gym classes.
     *
     * @return a list of all gym classes.
     */
    @GetMapping("/getGymClasses")
    public List<GymClass> getAllGymClasses()
    {
        return gymClassRepository.findAll();

    }
    
    /**
     * Adds a new gym class.
     *
     * @param GymClass the gym class to add.
     * @return a confirmation message of the addition.
     */
    @PostMapping("/insertGymClass")
    public String addCard(@RequestBody GymClass gymClass) {

        gymClassRepository.save(gymClass);
        return "Card added successfully!";
    }

    /**
     * Deletes a gym class by ID.
     *
     * @param id the ID of the gym class to delete.
     * @return true if the card was deleted, false otherwise.
     */
    @DeleteMapping("/deleteGymClass/{id}")
    public boolean deleteCard(@PathVariable int id) {
        if (gymClassRepository.existsById(id)) {
            gymClassRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Modifies a gym class.
     *
     * @param GymClass the card to modify.
     * @return true if the card was modified, false otherwise.
     */
    @PutMapping("/modifyGymClass")
    public boolean modifyGymClass(@RequestBody GymClass gymClass) {
        gymClassRepository.save(gymClass);
        return true;
    }


}
