package com.autenticacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.autenticacion.models.User;
import com.autenticacion.models.repositories.UsuarioRepository;


@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = usuarioRepository.buscarPorNombreUsuario(username).orElse(null);
		if (usuario == null)
			throw new UsernameNotFoundException("Username not found");
		return usuario;
	}

}
 