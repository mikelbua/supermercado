package com.ipartek.formacion.supermercado.modelo.dao;

import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

public interface IUsuarioDAO extends IDAO<Usuario>{
	
	/**
	 * Conprueba si existe el ususario en la base de datos
	 * 
	 * @param nombre
	 * @param contrasenia
	 * @return un usuario con datos si lo encuentra y un null si no lo encuentra.
	 */
	Usuario exist(String nombre, String contrasenia);

}
