package com.autenticacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.autenticacion.models.User;



public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.rol r WHERE u.username = :username")
	public Optional<User> buscarPorusername(String username);

	User findByUsername(String username);
	
}
