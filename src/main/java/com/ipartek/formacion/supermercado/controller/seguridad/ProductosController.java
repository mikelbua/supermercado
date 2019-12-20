package com.ipartek.formacion.supermercado.controller.seguridad;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.controller.Alerta;
import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/seguridad/productos")
public class ProductosController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW_TABLA = "productos/index.jsp";
	private static final String VIEW_FORM = "productos/formulario.jsp";
	private static String vistaSeleccionda = VIEW_TABLA;
	private static ProductoDAO dao;

	// acciones
	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_IR_FORMULARIO = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	// parametros
	String pAccion;
	String pId;
	String pNombre;
	String pPrecio;
	String pImagen;
	String pDescripcion;
	String pDescuento;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = ProductoDAO.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recoger parametros
		pAccion = request.getParameter("accion");

		pId = request.getParameter("id");
		pNombre = request.getParameter("nombre");
		pPrecio = request.getParameter("precio");
		pImagen = request.getParameter("imagen");
		pDescripcion = request.getParameter("descripcion");
		pDescuento = request.getParameter("descuento");

		try {

			switch (pAccion) {
			case ACCION_LISTAR:
				listar(request, response);
				break;
			case ACCION_ELIMINAR:
				eliminar(request, response);
				break;
			case ACCION_GUARDAR:
				guardar(request, response);
				break;
			case ACCION_IR_FORMULARIO:
				irFormulario(request, response);
				break;
			default:
				listar(request, response);
				break;
			}

		} catch (Exception e) {
			// TODO log
			e.printStackTrace();

		} finally {

			request.getRequestDispatcher(vistaSeleccionda).forward(request, response);
		}

	}
	
	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {

		Producto productoVisualizar = new Producto();
		int id = Integer.parseInt(pId);
		
		if (0 < id) { 
			
			productoVisualizar = dao.getById(id);

		}

		request.setAttribute("producto", productoVisualizar);
		vistaSeleccionda = VIEW_FORM;

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Producto product = new Producto();
		String mensaje = "";
		int id = Integer.parseInt(pId);
		
		if (0 == id) { 
			
			product.setNombre(pNombre);
			dao.create(product);
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY,"producto creao con exito"));
			
		} else {
			
			product.setId(id);
			product.setNombre(pNombre);
			dao.update(id, product);	
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY,"Datos guardados con exito"));
			
		}
		
		
		request.setAttribute("producto", product);
		request.setAttribute("mesaje", mensaje);
		vistaSeleccionda = VIEW_FORM;

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(pId);
		dao.delete(id);
		request.setAttribute("productos", dao.getAll());
		vistaSeleccionda = VIEW_TABLA;

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("productos", dao.getAll());
		vistaSeleccionda = VIEW_TABLA;

	}

}