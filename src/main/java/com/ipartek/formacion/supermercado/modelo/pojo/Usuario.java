package com.ipartek.formacion.supermercado.modelo.pojo;

import java.security.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Usuario {
	
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 50)
	private String nombre;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 50)
	private String contrasenia;
	
	
	public Usuario() {
		super();
		id=0;
		this.nombre = "";
		this.contrasenia = "";
	}

	public Usuario(int id, String nombre, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasenia=" + contrasenia + "]";
	}
	
	
	
	
	
}