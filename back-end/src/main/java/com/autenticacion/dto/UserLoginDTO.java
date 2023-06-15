package com.autenticacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserLoginDTO {

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
