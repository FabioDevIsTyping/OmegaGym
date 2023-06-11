package com.autenticacion.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.dto.UserDTO;
import com.autenticacion.dto.UserLoginDTO;
import com.autenticacion.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usuarioService;

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
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/area/administrador")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> accesoSoloAdministrador() {
		return new ResponseEntity<>("You are an admin", HttpStatus.OK);
	}

	@GetMapping("/area/usuario-restringido")
	@PreAuthorize("hasAuthority('USUARIO_RESTRINGIDO')")
	public ResponseEntity<?> accesoSoloUsuarioRestringido() {
		return new ResponseEntity<>("You are a user", HttpStatus.OK);
	}

	@GetMapping("/area/usuario-logueado")
	public ResponseEntity<?> accesoSoloUsuarioLogueas() {
		return new ResponseEntity<>("You are logged in", HttpStatus.OK);
	}

}
