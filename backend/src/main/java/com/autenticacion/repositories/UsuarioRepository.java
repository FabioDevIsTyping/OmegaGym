package com.autenticacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.autenticacion.models.User;



public interface UsuarioRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.rol r WHERE u.nombreUsuario = :nombreUsuario")
	public Optional<User> buscarPorNombreUsuario(String nombreUsuario);
	
}
