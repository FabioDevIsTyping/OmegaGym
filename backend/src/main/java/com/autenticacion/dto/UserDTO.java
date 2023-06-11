package com.autenticacion.dto;

import com.autenticacion.models.NombreRol;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

	private Long id;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@NotNull
	private NombreRol role;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public NombreRol getRole() {
		return role;
	}

	public void setRol(NombreRol newRole) {
		this.role = newRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}
