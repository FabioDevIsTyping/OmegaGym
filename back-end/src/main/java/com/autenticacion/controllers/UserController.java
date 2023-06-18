package com.autenticacion.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.autenticacion.dto.UserDTO;
import com.autenticacion.dto.UserLoginDTO;
import com.autenticacion.models.Card;
import com.autenticacion.models.User;
import com.autenticacion.repositories.CardRepository;
import com.autenticacion.repositories.UserRepository;
import com.autenticacion.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    /**
     * Logs in a user.
     *
     * @param usuarioLogin   the user's login details.
     * @param bindingResult  the result of the request validation.
     * @return a response entity with the user's data if the login is successful, an error message otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO usuarioLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("You need to insert username and password.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(usuarioService.login(usuarioLogin), HttpStatus.OK);
    }

    /**
     * Creates a new user.
     *
     * @param usuario        the user's data.
     * @param validaciones   the result of the request validation.
     * @return a response entity with the created user's data if the creation is successful, an error message otherwise.
     * @throws Exception     if an error occurs during the user creation process.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO usuario, BindingResult validaciones) throws Exception {
        if (validaciones.hasErrors()) {
            return new ResponseEntity<String>("You need to insert all information", HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<UserDTO>(usuarioService.crear(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("This username already exists.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a confirmation message indicating access to an admin-only area.
     *
     * @return a response entity with the confirmation message.
     */
    @GetMapping("/area/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> accessToOnlyAdmins() {
        return new ResponseEntity<>("You are an admin", HttpStatus.OK);
    }

    /**
     * Retrieves a confirmation message indicating access to a user-only area.
     *
     * @return a response entity with the confirmation message.
     */
    @GetMapping("/area/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> accessToOnlyUsers() {
        return new ResponseEntity<>("You are a user", HttpStatus.OK);
    }

    /**
     * Retrieves a confirmation message indicating access to a logged-in user area.
     *
     * @return a response entity with the confirmation message.
     */
    @GetMapping("/area/loggedUser")
    public ResponseEntity<?> accessToOnlyLoggedUser() {
        return new ResponseEntity<>("You are logged in", HttpStatus.OK);
    }

    /**
     * Retrieves a list of all users with the role "USER".
     *
     * @return a list of all users with the role "USER".
     */
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> filteredUserList = new ArrayList<>();

        userList.forEach(user -> {
            if (user.getRol().getId() == 2) {
                filteredUserList.add(user);
            }
        });

        return filteredUserList;
    }

    /**
     * Retrieves a list of all users without a card.
     *
     * @return a list of all users without a card.
     */
    @GetMapping("/getAllUsersWithoutCard")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsersWithoutCard() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<User> filteredUserList = new ArrayList<>();
        List<User> usersWithoutCard = new ArrayList<>();

        userList.forEach(user -> {
            if (user.getRol().getId() == 2) {
                filteredUserList.add(user);
            }
        });

        filteredUserList.forEach(user -> {
            if (cardRepository.findByUser(user) == null) {
                usersWithoutCard.add(user);
            }
        });

        return usersWithoutCard;
    }

    /**
     * Retrieves a single user based on the username.
     *
     * @param username  the username of the user to retrieve.
     * @return the user object.
     */
    @GetMapping("/getSingleUser/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getSingleUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Deletes a user given the ID.
     *
     * @param id    the ID of the user to delete.
     * @return true if the user was deleted successfully, false otherwise.
     */
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteUser(@PathVariable long id) {
        Card card = cardRepository.findByUser(userRepository.findById(id).get());
        cardRepository.delete(card);
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Retrieves the count of users with the role "USER".
     *
     * @return the count of users with the role "USER".
     */
    @GetMapping("/getUsersCount")
    @PreAuthorize("hasAuthority('USER')")
    public int getUsersCount() {
        int count = 0;
        List<User> userList = (List<User>) userRepository.findAll();

        for (User user : userList) {
            if (user.getRol().getId() == 2) {
                count++;
            }
        }

        return count;
    }
}
