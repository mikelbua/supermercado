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
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

public class ProductoDAO implements IProductoDAO{

	private final static Logger LOG = Logger.getLogger(ProductoDAO.class);
	
	private static ProductoDAO INSTANCE;
	
	
	private static final String SQL_GET_ALL = "SELECT p.id 'id_producto', p.nombre 'nombre_producto', p.precio 'precio' , p.foto 'foto' , p.descuento, u.id 'id_usuario', u.nombre 'nombre_usuario' "
			+ " FROM producto p, usuario u " + " WHERE p.id_usuario = u.id " + " ORDER BY p.id DESC LIMIT 500;";

	private static final String SQL_GET_ALL_BY_USER = "SELECT p.id 'id_producto', p.nombre 'nombre_producto', p.precio 'precio' , p.foto 'foto' , p.descuento, u.id 'id_usuario', u.nombre 'nombre_usuario'"
			+ " FROM producto p, usuario u " + " WHERE p.id_usuario = u.id AND u.id = ? "
			+ " ORDER BY p.id DESC LIMIT 500;";
	
	private static final String SQL_GET_BY_ID_BY_USER = "SELECT p.id 'id_producto', p.nombre 'nombre_producto', p.precio , p.foto, p.descuento, u.id 'id_usuario', u.nombre 'nombre_usuario' "
			+ " FROM producto p, usuario u " 
			+ " WHERE p.id_usuario = u.id AND p.id= ? AND u.id = ?"
			+ " ORDER BY p.id DESC LIMIT 500;";
	
	private static final String SQL_GET_BY_ID = "SELECT 	p.id 'id_producto', p.nombre 'nombre_producto' , p.precio, p.foto 'foto' , p.descuento ,u.id 'id_usuario', u.nombre 'nombre_usuario'" 
			+ "	FROM producto p, usuario u " 
			+ "	WHERE p.id_usuario = u.id AND p.id= ?" 
			+ "	ORDER BY p.id DESC LIMIT 500;";

	private static final String SQL_GET_INSERT = "INSERT INTO `producto` (`nombre`, `id_usuario`) VALUES (?, ?);";
	
	private static final String SQL_GET_UPDATE = "UPDATE `producto` SET `nombre`= ? , `id_usuario`= ? WHERE `id`= ? ;";
	private static final String SQL_GET_UPDATE_BY_USER = "UPDATE `producto` SET `nombre`= ? , `id_usuario`= ? WHERE `id`= ? AND id_usuario = ? ;";
	
	private static final String SQL_DELETE = "DELETE FROM producto WHERE id = ? ;";
	private static final String SQL_DELETE_BY_USER = "DELETE FROM producto WHERE id = ? AND id_usuario = ? ;";
	
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

				lista.add(mapper(rs));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	
	public List<Producto> getAllByUser(int idUsuario) {

		ArrayList<Producto> lista = new ArrayList<Producto>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_USER);) {

			pst.setInt(1, idUsuario);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					lista.add(mapperListaUser(rs));
				}
			} // executeQuery

		} catch (SQLException e) {
			LOG.error(e);
		}

		return lista;
	}
	
	
	@Override
	public Producto getById(int id) {
		
		Producto resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);) {

			//sustituyE parametros en la SQL, en este caso 1º ? por id
			pst.setInt(1, id);
			
			LOG.debug(pst);
			
			//ejecuto la consulta
			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					resul = mapper(rs);
				}
			}

		} catch (SQLException e) {
			LOG.error(e);
		}

		return resul;
	}
	
	
	@Override
	public Producto getByIdByUser(int idProducto, int idUsuario) throws ProductoException, SQLException {
		
		Producto resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID_BY_USER);) {

			//sustituyE parametros en la SQL, en este caso 1º ? por idProducto 2º ? idUsuario
			pst.setInt(1, idProducto);
			pst.setInt(2, idUsuario);
			
			LOG.debug(pst);
			
			//ejecuto la consulta
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					resul = mapper(rs);
				}
				else {
					LOG.warn("no se encuentra producto");
					throw new ProductoException(ProductoException.EXCEPTION_UNAUTORIZED);
				}
			}

			return resul;
		}//try
	}
	

	@Override
	public Producto delete(int id) throws Exception {
		Producto resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE)) {

			pst.setInt(1, id);
			LOG.debug(pst);
			resul = this.getById(id);//Recuperar
			
			int affectedRows = pst.executeUpdate();//Eliminar
			if (affectedRows != 1) {
				resul = null;
				throw new Exception("No se puede eliminar " + resul);
			}

		}

		return resul;
	}
	
	
	@Override
	public Producto deleteByUser(int idProducto, int idUsuario) throws ProductoException {
		Producto resul = null;

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_USER)) {

			pst.setInt(1, idProducto);
			pst.setInt(2, idUsuario);			
			
			resul = this.getById(idProducto); //Recuperar
			
			int affectedRows = pst.executeUpdate();  //Eliminar
			if (affectedRows == 1) {
				LOG.debug("reistro borrado");
				
			}else if (affectedRows == 0) {
				LOG.error("no te pertenece este producto");
				throw new ProductoException( ProductoException.EXCEPTION_UNAUTORIZED );
				}
			else {
				LOG.error("no existe el producto");
				throw new ProductoException( ProductoException.EXCEPTION_UNAUTORIZED );
				}
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProductoException(ProductoException.EXCEPTION_UNAUTORIZED);
		}
		return resul;
	}
	

	@Override
	public Producto update(int id, Producto pojo) throws Exception {
		

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_UPDATE)) {

			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getUsuario().getId());
			pst.setInt(3, id);

			int affectedRows = pst.executeUpdate(); // lanza una excepcion si nombre repetido
			if (affectedRows == 1) {
				pojo.setId(id);
				LOG.debug("producto modificado.");
			} else {
				throw new Exception("No se encontro registro para id =" + id);
			}

		}
		return pojo;
	}
	
	
	@Override
	public Producto updateByUser(int idProducto, int idUsuario, Producto pojo) throws ProductoException {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_UPDATE_BY_USER)) {

			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getUsuario().getId());
			pst.setInt(3, idProducto);
			pst.setInt(4, idUsuario);

			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				LOG.debug("Producto modificado correctamente");
			} else {
				LOG.warn("Este producto no le pertenece.");
				throw new ProductoException(ProductoException.EXCEPTION_UNAUTORIZED);
			}
			
		} catch (SQLException e) {
			LOG.warn("El nombre ya existe");
		}
		return pojo;
	}
	

	@Override
	public Producto create(Producto pojo) throws Exception {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_INSERT, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getUsuario().getId());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				// conseguimos el ID que acabamos de crear
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					pojo.setId(rs.getInt(1));
				}

			}

		}//try

		return pojo;
	}
	
	
	private Producto mapper(ResultSet rs) throws SQLException {

		Producto p = new Producto();
		p.setId(rs.getInt("id_producto"));
		p.setNombre(rs.getString("nombre_producto"));
		p.setPrecio(rs.getFloat("precio"));
		p.setFoto(rs.getString("foto"));
		p.setDescuento(rs.getInt("descuento"));
	
		Usuario u = new Usuario();
		u.setId(rs.getInt("id_usuario"));
		u.setNombre(rs.getString("nombre_usuario"));
		p.setUsuario(u);

		return p;
	}
	
	
	
	private Producto mapperListaUser(ResultSet rs) throws SQLException {

		Producto p = new Producto();
		p.setId(rs.getInt("id_producto"));
		p.setNombre(rs.getString("nombre_producto"));
		
		Usuario u = new Usuario();
		u.setId(rs.getInt("id_usuario"));
		u.setNombre(rs.getString("nombre_usuario"));
		p.setUsuario(u);

		return p;
	}

	
	
	
}//class final
