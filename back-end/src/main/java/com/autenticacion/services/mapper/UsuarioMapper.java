package com.autenticacion.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.autenticacion.dto.UserDTO;
import com.autenticacion.models.User;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Mapping(source = "rol.nombreRol", target = "rol")
	public UserDTO toUsuarioDTO(User usuario);
	
	@Mapping(target = "authorities", ignore = true)
	public User toUsuario(UserDTO usuarioDTO);
	
}
