package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.dao.IDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

public class ProductoDAO implements IDAO<Producto>{

	private static ProductoDAO INSTANCE;
	private ArrayList<Producto> registros;
	
	
	private static final String SQL_GET_ALL = "SELECT id, nombre FROM producto ORDER BY id DESC LIMIT 500;";

	private ProductoDAO() {		
		super();			
	}
	
	public synchronized static ProductoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ProductoDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Producto> getAll() {
		
		ArrayList<Producto> lista = new ArrayList<Producto>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				Producto p = new Producto();
				p.setId( rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				lista.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@SuppressWarnings("null")
	@Override
	public Producto getById(int id) {
		
		Producto resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement("SELECT id,nombre FROM producto WHERE id= "+id+";");
				ResultSet rs = pst.executeQuery()) {
			
			resul = new Producto();
			resul.setId( rs.getInt("id"));
			resul.setNombre(rs.getString("nombre"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resul;
	}

	@Override
	public Producto delete(int id) throws Exception {
		Producto resul = null;

		for (Producto producto : registros) {
			if (id == producto.getId()) {
				resul = producto;
				registros.remove(producto);
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el producto que habia que borrar." + "ID = "+id);
		}

		return resul;
	}

	@Override
	public Producto update(int id, Producto pojo) throws Exception {
		
		Producto resul = null;
		
		for (int i = 0; i < registros.size(); i++) {
			if (id == registros.get(i).getId()) {
				registros.remove(i);
				registros.add(pojo);
				resul = pojo;
				break;
			}
		}
		if (resul == null) {
			throw new Exception("no ha encontrado el perro qeu habia que borrar" + id);
		}
		return resul;
	}

	@Override
	public Producto create(Producto pojo) throws Exception {
		Producto resul = null;
		if (pojo != null) {
			registros.add(pojo);
		} else {
			throw new Exception("perro NULL sin datos");
		}
		
		return resul;
	}
}
