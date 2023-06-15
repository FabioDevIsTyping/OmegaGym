package com.autenticacion.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.autenticacion.services.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtDetails extends OncePerRequestFilter {

	
	@Autowired
	private UserDetailsImpl detalleUsuario;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = getToken(request);
			if (token != null && JwtToken.validarTokenJWT(token)) {
				String username = JwtToken.tokenRecognizeString(token);
				if (username != null) {
					UserDetails userDatail = detalleUsuario.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDatail, true,
							userDatail.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch (Exception e) {
			
		}

		filterChain.doFilter(request, response);

	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}

		return null;
	}

}
