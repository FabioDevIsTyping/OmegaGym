package com.autenticacion.services;

import com.autenticacion.dto.UserDTO;
import com.autenticacion.dto.UserLoginDTO;

public interface UserService  {
	public UserDTO login(UserLoginDTO usuarioLoginDTO);

	public UserDTO crear(UserDTO usuarioDTO) throws Exception;

	
}
