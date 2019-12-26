package com.ipartek.formacion.supermercado.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.model.ConnectionManager;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

	private final static Logger LOG = Logger.getLogger(UsuarioDAO.class);
	private static final String SQL_EXIST = "SELECT id , nombre , contrasenia FROM usuario WHERE nombre = ? AND contrasenia = ?;";
	private static final String SQL_GET_ALL = "SELECT id , nombre , contrasenia FROM usuario;";

	private static final String SQL_GET_BY_ID ="SELECT id, nombre ,contrasenia FROM usuario WHERE id = ? ;"; 
	private static final String SQL_GET_INSERT ="INSERT INTO usuario (nombre , contrasenia) VALUES ( ?  ,  ? );";
	private static final String SQL_GET_UPDATE ="UPDATE usuario SET nombre = ? , contrasenia= ? WHERE id = ? ;";
	private static final String SQL_DELETE ="DELETE FROM usuario WHERE id = ? ;";
	
	private static UsuarioDAO INSTANCE = null;

	public synchronized static UsuarioDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}

		return INSTANCE;
	}

	@Override
	public List<Usuario> getAll() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				Usuario u = new Usuario();
				u.setId( rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setContrasenia(rs.getString("contrasenia"));
				lista.add(u);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Usuario getById(int id) {
		
		Usuario resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);
				) {
			
			// sustituyo parametros en la SQL, en este caso 1º ? por id			
						pst.setInt(1, id);
						
						//ejecuto la consulta
						try( ResultSet rs = pst.executeQuery() ){

							while (rs.next()) {
								
								resul = new Usuario();
								resul.setId( rs.getInt("id"));
								resul.setNombre(rs.getString("nombre"));
								resul.setContrasenia(rs.getString("contrasenia"));
									
							}
						}	

					} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resul;
	}

	@Override
	public Usuario delete(int id) throws Exception {
		
		Usuario resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE)) {

			pst.setInt(1, id);			
			
			resul = this.getById(id); //Recuperar
			
			
			int affectedRows = pst.executeUpdate();  //Eliminar
			if (affectedRows != 1) {
				resul = null;
				throw new Exception("No se puede eliminar " + resul);
			}

		}

		return resul;
	}

	@Override
	public Usuario update(int id, Usuario pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_UPDATE)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getContrasenia());
			pst.setInt(3, id);
			
			int affectedRows = pst.executeUpdate();  // lanza una excepcion si nombre repetido
			if (affectedRows == 1) {
				pojo.setId(id);				
			}else {
				throw new Exception("No se encontro registro para id=" + id);
			}

		}
		return pojo; 
	}

	@Override
	public Usuario create(Usuario pojo) throws Exception {

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement( SQL_GET_INSERT, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getContrasenia());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				// conseguimos el ID que acabamos de crear
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					pojo.setId(rs.getInt(1));
				}

			}

		}

		return pojo;
	}

	@Override
	public Usuario exist(String nombre, String contrasenia) {
		Usuario result = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_EXIST);) {

			pst.setString(1, nombre);
			pst.setString(2, contrasenia);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					//mapear el RS al POJO.
					result = new Usuario();
					result.setId((rs.getInt("id")));
					result.setNombre((rs.getString("nombre")));
					result.setContrasenia((rs.getString("contrasenia")));

				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return result;
	}

}
