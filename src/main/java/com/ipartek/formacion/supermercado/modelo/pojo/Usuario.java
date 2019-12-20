package com.ipartek.formacion.supermercado.modelo.pojo;

import java.security.Timestamp;

public class Usuario {
	
	private int id;
	private String nombre;
	private String contrasenya;
	private Timestamp FechaCreacion;
	private Timestamp FechaEliminacion;
	
	
	public Usuario(int id, String nombre, String contrasenya, Timestamp fechaCreacion, Timestamp fechaEliminacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		FechaCreacion = fechaCreacion;
		FechaEliminacion = fechaEliminacion;
		
		
	}
	
	public Usuario() {
		super();
		this.nombre = "";
		this.contrasenya = "";
		FechaCreacion = null;
		FechaEliminacion = null;		
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

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public Timestamp getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaEliminacion() {
		return FechaEliminacion;
	}

	public void setFechaEliminacion(Timestamp fechaEliminacion) {
		FechaEliminacion = fechaEliminacion;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasenya=" + contrasenya + ", FechaCreacion="
				+ FechaCreacion + ", FechaEliminacion=" + FechaEliminacion + "]";
	}
	
	
	
	
	
}
