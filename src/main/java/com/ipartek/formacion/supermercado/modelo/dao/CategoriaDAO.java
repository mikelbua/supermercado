package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;

public class CategoriaDAO implements ICategoriaDAO {

	private final static Logger LOG = Logger.getLogger(CategoriaDAO.class);

	private static CategoriaDAO INSTANCE;

	public synchronized static CategoriaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CategoriaDAO();
		}

		return INSTANCE;
	}

	public List<Categoria> getAll() {
		LOG.trace("Lista de categorias");
		ArrayList<Categoria> lista = new ArrayList<Categoria>();

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_getall() }");) {

			LOG.debug(cs);

			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					Categoria c = new Categoria();
					c = mapper(rs);
					lista.add(c);
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return lista;

	}//getAll

	@Override
	public Categoria getById(int id) {
		Categoria resul = null;

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_getbyid( ? ) }");) {

			// sustituye parametros en la SQL, en este caso 1º ? por id
			cs.setInt(1, id);

			LOG.debug(cs);

			// ejecuto la consulta
			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					resul = mapper(rs);// mapea el resultadod e la consulta
				}
			}

		} catch (SQLException e) {
			LOG.error(e);
		}

		return resul;
	}//getById

	@Override
	public Categoria delete(int id) throws Exception {

		Categoria resul = null;

		LOG.trace("Borrar  categoria de id = " + id);

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_delete( ? ) }");) {

			cs.setInt(1, id);

			LOG.debug(cs);

			resul = this.getById(id);// Recuperar el producto que se va a eliminar por si queremos usar sus datos

			int affectedRows = cs.executeUpdate();

			if (affectedRows != 1) {
				resul = null;
				throw new Exception("No se puede eliminar " + resul);
			}

		}

		return resul;
	}//delete

	@Override
	public Categoria update(int id, Categoria pojo) throws Exception {
		
		Categoria resul = null;

		LOG.trace("Actualozar registro de id = " + id);

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_update( ? , ? ) }");) {

			cs.setString(1, pojo.getNombre());
			cs.setInt(2, id);

			LOG.debug(cs);


			int affectedRows = cs.executeUpdate();
			if (affectedRows != 1) {
				resul = null;
				throw new Exception("No se puede actualizar la categoria :  " + resul);
			}

		}

		return resul;
	}//update

	@Override
	public Categoria create(Categoria pojo) throws Exception {

		LOG.trace("insertar nueva categoria" + pojo);

		try (Connection con = ConnectionManager.getConnection();
				CallableStatement cs = con.prepareCall("{ CALL pa_categoria_insert( ? , ?) }");) {

			// parametro de entrada
			cs.setString(1, pojo.getNombre());

			// registro del parametro de salida
			cs.registerOutParameter(2, java.sql.Types.INTEGER);

			// se ejecuta el procedimiento almacenado executeUpdate(por que es una insert.
			// si fuera SELECT seria executeQuery()
			cs.executeUpdate();

		} // try

		return pojo;

	}// create

	private Categoria mapper(ResultSet rs) throws SQLException {

		Categoria c = new Categoria();
		c.setId(rs.getInt("id_categoria"));
		c.setNombre(rs.getString("nombre_categoria"));

		return c;
	}

}// class
