package com.ipartek.formacion.supermercado.modelo.dao;
import java.sql.SQLException;
import java.util.List;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

public interface IProductoDAO extends IDAO<Producto> {

	/**
	 * Lista los prductos de un usuario en concreto.
	 * 
	 * @param idUsuario
	 * @return List<Producto>, lista inicializada en caso de que no tenga productos.
	 */
	List<Producto> getAllByUser(int idUsuario);

	/**
	 * Recupera un unico producto de un ususario concreto.
	 * 
	 * @param idProducto
	 * @return Producto
	 * @exception ProductoException cuando el producto que intenta recuperar no
	 *                              pertenece a ese usuario
	 */

	Producto getByIdByUser(int idProducto, int idUsuario) throws ProductoException , SQLException;
	
	/**
	 * Modifica un producto de un usuario concreto.
	 * 
	 * @param id
	 * @param pojo
	 * @return El producto modificado.
	 * @throws Exception
	 */
	public  Producto updateByUser(int idProducto, int idUsuario, Producto pojo)throws ProductoException;
	
	/**
	 * 
	 * @param id
	 * @return El producto eliminado si lo encuentra , sino una ProductoException.
	 * @throws ProductoException
	 * <ol>
	 * 		<li>Cuando intenta eliminar un producto que no le pertenece.</li>
	 * 		<li>Cuando no encuentra el producto por su id para idUsuario.</li>
	 * </ol>
	 */
	
	public Producto deleteByUser(int idProducto, int idUsuario) throws ProductoException;

}// class
