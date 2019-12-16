package com.ipartek.formacion.supermercado.controller;

public class Alerta {

	public static final String TIPO_PRIMARY="primary" ;
	public static final String TIPO_DANGER="danger";
	
	private String texto="";
	private String tipo ="";
	
	
	public Alerta(String tipo ,String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}
	
	public Alerta() {
		super();
		this.texto = "Error inesperado de la aplicacion";
		this.tipo = "danger";
	}
	


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Alerta [texto=" + texto + ", tipo=" + tipo + "]";
	}
	
	
	
	
	
}
