package com.autenticacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.autenticacion.dto.UserDTO;
import com.autenticacion.dto.UserLoginDTO;
import com.autenticacion.jwt.JwtToken;
import com.autenticacion.models.Role;
import com.autenticacion.models.User;
import com.autenticacion.repositories.RoleRepository;
import com.autenticacion.repositories.UsuarioRepository;
import com.autenticacion.services.mapper.UsuarioMapper;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private RoleRepository rolRepository;

	@Override
	public UserDTO crear(UserDTO usuarioDTO) throws Exception  {

		User usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario.setClave(passwordEncoder.encode(usuarioDTO.getPassword()));
		Role rol = rolRepository.findByNombreRol(usuarioDTO.getRole()).orElseThrow(()-> new Exception("This role doesnt exist in the database, you need to insert it in your database first!"));
		usuario.setRol(rol);
		usuario = usuarioRepository.save(usuario);
		return usuarioMapper.toUsuarioDTO(usuario);
		
	}


	@Override
	public UserDTO login(UserLoginDTO usuarioLoginDTO) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);

		String token = JwtToken.generarTokenJWT(usuarioLoginDTO.getUsername());

		User usuario = usuarioRepository.buscarPorNombreUsuario(usuarioLoginDTO.getUsername()).orElse(null);
		
		UserDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);

		usuarioDTO.setToken(token);
		return usuarioDTO;
	}	

}
