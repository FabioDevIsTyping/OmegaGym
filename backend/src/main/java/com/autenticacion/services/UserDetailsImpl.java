package com.autenticacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.autenticacion.models.User;
import com.autenticacion.repositories.UserRepository;


@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = usuarioRepository.buscarPorusername(username).orElse(null);
		if (usuario == null)
			throw new UsernameNotFoundException("Username not found");
		return usuario;
	}

}
 