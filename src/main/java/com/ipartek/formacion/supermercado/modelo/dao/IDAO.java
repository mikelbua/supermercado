package com.ipartek.formacion.supermercado.modelo.dao;

import java.util.List;


public interface IDAO<P> {

	/**
	 * Creamos las funciones qeu despues deberan implementar todos los que utilicen la interface
	 * @return
	 */
	
	/**
	 * Obtiene una lista de todos los datos del objeto que le pasamos lista de pojos.
	 * @return Lista de objetos.
	 */
	public List<P> getAll();
	/**
	 * Obtendra todos los datos de un unico objeto.
	 * @param id
	 * @return Objeto
	 */
	public  P getById(int id);
	/**
	 * Borrara el objeto y nos devolvera los datos del objeto eliminado.
	 * 
	 * @param id
	 * @return El objeto eliminado.
	 * @throws Exception - Si no es posible lazara una exception. O sino lo encuentr.
	 */
	public  P delete(int id) throws Exception;
	/**
	 * Modificara(Actualizara) el objeto que pasamos por parametro.
	 * Si no es posible lazara una exception.
	 * 
	 * @param id
	 * @param pojo
	 * @return Devuelve el objeto modificado.
	 * @throws Exception - Si no es posible lazara una exception. O sino lo encuentr.
	 */
	public  P update(int id, P pojo)throws Exception;
	
	/**
	 * Crea un objeto.
	 * 
	 * @param pojo
	 * @return El objeto creado con el id nuevo.
	 * @throws Exception -Si no es posible lazara una exception. O sino lo encuentr.
	 */
	public  P create(P pojo)throws Exception;
}
