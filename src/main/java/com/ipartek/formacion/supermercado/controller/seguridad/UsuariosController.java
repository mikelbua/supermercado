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

import com.ipartek.formacion.supermercado.controller.Alerta;
import com.ipartek.formacion.supermercado.modelo.dao.IUsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class UsuariosController
 */
@WebServlet("/seguridad/usuarios")
public class UsuariosController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW_TABLA = "usuarios/index.jsp";
	private static final String VIEW_FORM = "usuarios/formulario.jsp";
	private static String vistaSeleccionda = VIEW_TABLA;
	private static UsuarioDAO dao;

	// acciones
	public static final String ACCION_LISTAR = "listar";
	public static final String ACCION_IR_FORMULARIO = "formulario";
	public static final String ACCION_GUARDAR = "guardar"; // crear y modificar
	public static final String ACCION_ELIMINAR = "eliminar";

	//Crear Factoria y Validador las inicializamos en el metidi init ma abajo.
	 ValidatorFactory factory;
	 Validator validator;
	
	
	
	// parametros
	String Accion;
	String uId;
	String uNombre;
	String uContrasenia;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		dao = UsuarioDAO.getInstance();
		
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		
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
		Accion = request.getParameter("accion");

		uId = request.getParameter("id");
		uNombre = request.getParameter("nombre");
		uContrasenia = request.getParameter("contrasenia");

		try {

			switch (Accion) {
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
			
			e.printStackTrace();

		} finally {

			request.getRequestDispatcher(vistaSeleccionda).forward(request, response);
		}

	}
	
	private void irFormulario(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioVisualizar = new Usuario();
		if ( uId != null) {
			
			int id = Integer.parseInt(uId);
			if(id > 0) {
				usuarioVisualizar = dao.getById(id);
			} else {
				usuarioVisualizar.setId(id);
			}
		}
		
		
		request.setAttribute("usuario", usuarioVisualizar );
		vistaSeleccionda = VIEW_FORM;

	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(uId);
		Usuario uGuardar = new Usuario();	
		
		uGuardar.setId(id);
		uGuardar.setNombre(uNombre);
		uGuardar.setContrasenia(uContrasenia);
		
		
		Set<ConstraintViolation<Usuario>> validaciones = validator.validate(uGuardar);
		if( validaciones.size() > 0 ) {			
			mensajeValidacion(request, validaciones);//este metodo valida y devuelve mensajes.
		}else {	
		
				try {
				
					if ( id > 0 ) {  // modificar
						
						dao.update(id, uGuardar);		
						request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Usuario modificado con exito. Vaya a la tabla de usuarios para visualizarlo."));
					}else {            // crear
						dao.create(uGuardar);
						request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Usuario creado con exito. Vaya a la tabla de usuarios para visualizarlo."));
					}
					
				}catch (Exception e) {  // validacion a nivel de base datos
					
					request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "El nombre ya existe, selecciona otro"));
				}					
			
			
		}
		
		request.setAttribute("usuario", uGuardar);
		vistaSeleccionda = VIEW_FORM;

	}
	
	private void mensajeValidacion(HttpServletRequest request, Set<ConstraintViolation<Usuario>> validaciones ) {

		StringBuilder mensaje = new StringBuilder();
		for (ConstraintViolation<Usuario> cv : validaciones) {
			
			mensaje.append("<p>");
			mensaje.append(cv.getPropertyPath()).append(": ");
			mensaje.append(cv.getMessage());
			mensaje.append("</p>");
			
		}
		
		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, mensaje.toString() ));
		
	}
	
	

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(uId);
		try {
			Usuario uEliminado = dao.delete(id);
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Eliminado " + uEliminado.getNombre() ));
		} catch (Exception e) {
			request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER, "No se puede Eliminar el usuario"));
			
		}
		
		listar(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuarios", dao.getAll());
		vistaSeleccionda = VIEW_TABLA;

	}


}
