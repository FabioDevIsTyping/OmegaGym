package com.autenticacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Enumerated(EnumType.STRING)
    private NombreRol nombreRol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NombreRol getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(NombreRol nombreRol) {
		this.nombreRol = nombreRol;
	}
    
    

}
