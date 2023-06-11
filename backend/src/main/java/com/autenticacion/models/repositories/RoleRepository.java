package com.autenticacion.models.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.autenticacion.models.NombreRol;
import com.autenticacion.models.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByNombreRol(NombreRol nombreRol);
}
