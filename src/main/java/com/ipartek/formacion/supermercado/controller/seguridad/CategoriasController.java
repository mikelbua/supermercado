package com.ipartek.formacion.supermercado.controller.seguridad;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.controller.Alerta;
import com.ipartek.formacion.supermercado.modelo.dao.CategoriaDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;

/**
 * Servlet implementation class CategoriasController
 */
@WebServlet("/seguridad/categorias")
public class CategoriasController extends HttpServlet {

	private final static Logger LOG = Logger.getLogger(SeguridadBackofficeFilter.class);
	private static final long serialVersionUID = 1L;
	private static final String VIEW_TABLA = "categorias/index.jsp";
	private static final String VIEW_FORM = "categorias/formulario.jsp";
	private static String vistaSeleccionda = VIEW_TABLA;
	private static CategoriaDAO daoCategoria;

	// acciones
	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_IR_FORMULARIO = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	//Crear Factoria y Validador las inicializamos en el metidi init ma abajo.
	 ValidatorFactory factory;
	 Validator validator;
	
	
	
	// parametros
	String cAccion;
	String cId;
	String cNombre;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		daoCategoria = CategoriaDAO.getInstance();
		
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		super.destroy();
		daoCategoria = null;
		
		factory = null;
		validator = null;
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
		cAccion = request.getParameter("accion");

		cId = request.getParameter("id");
		cNombre = request.getParameter("nombre");
		
		try {

			switch (cAccion) {
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
			
			LOG.error(e);

		} finally {

			request.getRequestDispatcher(vistaSeleccionda).forward(request, response);
		}

	}
	
	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {

		Categoria cEditar = new Categoria();
		
		if ( cId != null ) {
			
			int id = Integer.parseInt(cId);
			cEditar = daoCategoria.getById(id);
			
		}
		
		request.setAttribute("categoria", cEditar );
		vistaSeleccionda = VIEW_FORM;

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(cId);
		Categoria cGuardar = new Categoria();	
		
		cGuardar.setId(id);
		cGuardar.setNombre(cNombre);
		
		
		Set<ConstraintViolation<Categoria>> validaciones = validator.validate(cGuardar);
		if( validaciones.size() > 0 ) {			
			mensajeValidacion(request, validaciones);//este metodo valida y devuelve mensajes.
		}else {	
		
				try {
				
					if ( id > 0 ) {  // modificar
						
						daoCategoria.update(id, cGuardar);		
						request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Producto modificado con exito. Vaya a la tabla para visualizarlo."));
					}else {            // crear
						daoCategoria.create(cGuardar);
						request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Producto creado con exito. Vaya a la tabla para visualizarlo."));
					}
					
				}catch (Exception e) {  // validacion a nivel de base datos
					
					request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "El nombre ya existe, selecciona otro"));
				}					
			
			
		}
		
		request.setAttribute("categoria", cGuardar);
		vistaSeleccionda = VIEW_FORM;

	}
	
	private void mensajeValidacion(HttpServletRequest request, Set<ConstraintViolation<Categoria>> validaciones ) {

		StringBuilder mensaje = new StringBuilder();
		for (ConstraintViolation<Categoria> cv : validaciones) {
			
			mensaje.append("<p>");
			mensaje.append(cv.getPropertyPath()).append(": ");
			mensaje.append(cv.getMessage());
			mensaje.append("</p>");
			
		}
		
		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, mensaje.toString() ));
		
	}
	
	

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(cId);
		try {
			Categoria cEliminado = daoCategoria.delete(id);
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Eliminado " + cEliminado.getNombre() ));
		} catch (Exception e) {
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "No se puede Eliminar la categoria"));
			
		}
		
		listar(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("categorias", daoCategoria.getAll());
		vistaSeleccionda = VIEW_TABLA;

	}

}