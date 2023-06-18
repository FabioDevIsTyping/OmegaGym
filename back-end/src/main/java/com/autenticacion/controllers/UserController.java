package com.autenticacion.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO usuarioLogin, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("You need to insert username and password.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<UserDTO>(usuarioService.login(usuarioLogin), HttpStatus.OK);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> crear(@Valid @RequestBody UserDTO usuario, BindingResult validaciones)
			throws Exception {
		if (validaciones.hasErrors()) {
			return new ResponseEntity<String>("You need to insert all informations", HttpStatus.BAD_REQUEST);
		}

		try {
			return new ResponseEntity<UserDTO>(usuarioService.crear(usuario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("This username already exists.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/area/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> accessToOnlyAdmins() {
		return new ResponseEntity<>("You are an admin", HttpStatus.OK);
	}

	@GetMapping("/area/user")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> accessToOnlyUsers() {
		return new ResponseEntity<>("You are a user", HttpStatus.OK);
	}

	@GetMapping("/area/loggedUser")
	public ResponseEntity<?> accessToOnlyLoggedUser() {
		return new ResponseEntity<>("You are logged in", HttpStatus.OK);
	}

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
			if(cardRepository.findByUser(user)==null){
				usersWithoutCard.add(user);
			}
		});
		return usersWithoutCard;
	}



	@GetMapping("/getSingleUser/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User getSingleUser(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean deleteUser(@PathVariable long id) {
		Card card = cardRepository.findByUser(userRepository.findById(id).get());
		cardRepository.delete(card);
		userRepository.deleteById(id);
		return true;
	}

	@GetMapping("/getUsersCount")
	@PreAuthorize("hasAuthority('USER')")
	public int getUsersCount() {
		int count = 0;
		List<User> userList = (List<User>) userRepository.findAll();

		for (User user : userList) {
			if (user.getRol().getId() == 2) { // Assuming role ID 2 represents "USER" role
				count++;
			}
		}

		return count;
	}

}
