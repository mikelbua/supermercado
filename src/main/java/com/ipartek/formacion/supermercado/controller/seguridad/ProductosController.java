package com.ipartek.formacion.supermercado.controller.seguridad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/seguridad/producto")
public class ProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_TABLA = "producto/index.jsp";
	private static final String VIEW_FORM = "productos/formulario.jsp";
	private static String FORWARD = VIEW_TABLA;
	
	private ProductoDAO dao;
	
	//acciones
	private static final String ACCION_LISTAR = "listar";
	private static final String ACCION_IR_FORMULARIO = "formulario";
	private static final String ACCCION_GUARDAR = "guardar";//crear modificar
	private static final String ACCCION_ELIMINAR = "eliminar";
	
	//parametros los declaramos aqui par qeu sean globales 
	//y poder usarlos en nuestras funciones (eliminar,crear,modificar...)
	String pAccion;
	
	String pId;
	String pNombre;
	String pPrecio;
	String pFoto;
	String pDescripcion;
	String pDecuento;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = ProductoDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recogemos todos los parametros.
		String pAccion = request.getParameter("accion");
		
		String pId = request.getParameter("id");
		String pNombre = request.getParameter("nombre");
		String pPrecio = request.getParameter("precio");
		String pFoto = request.getParameter("foto");
		String pDescripcion = request.getParameter("descripcion");
		String pDecuento = request.getParameter("descuento");
		
		try {
			//TODO logica de negocio
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			request.getRequestDispatcher("").forward(request, response);
		}
		
	}

}
