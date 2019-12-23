package com.ipartek.formacion.supermercado.modelo.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Producto {

	public static final int DESCUENTO_MIN=1;
	public static final int DESCUENTO_MAX=100;
	
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 50)
	private String nombre;
	
	private float precio;
	
	private String foto;
	
	
	private String descripcion;
	
	@Min(0)
	@Max(100)
	private int descuento;//Este atributo sera un numero entre 1 y 100.
	
	
	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0;
		this.foto = "https://www.gs1es.org/wp-content/uploads/2016/02/Producto-Fresco.png";
		this.descripcion = "";
		this.descuento = DESCUENTO_MIN;
	}
	

	public Producto(int id, String nombre, float precio, String foto, String descripcion, int descuento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.foto = foto;
		this.descripcion = descripcion;
		this.descuento = descuento;
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


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getDescuento() {
		return descuento;
	}


	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}


	public static int getDescuentoMin() {
		return DESCUENTO_MIN;
	}


	public static int getDescuentoMax() {
		return DESCUENTO_MAX;
	}


	public float getPrecioDescuento(){
		float resul=0;
		
		return (  (this.precio * ( 100 - this.descuento )) / 100  );
		
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", foto=" + foto + ", descripcion="
				+ descripcion + ", descuento=" + descuento + "]";
	}
	
	
	
	
	
	
	
	
	
}
