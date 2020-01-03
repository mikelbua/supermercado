package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.dao.UsuarioDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Rol;
import com.ipartek.formacion.supermercado.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger LOG = Logger.getLogger(UsuarioDAO.class);
	private static UsuarioDAO usuarioDao = UsuarioDAO.getInstance();

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		usuarioDao = UsuarioDAO.getInstance();

	}

	@Override
	public void destroy() {

		super.destroy();
		usuarioDao = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// parametros del form
		String view = "login.jsp";
		String nombre = (String) request.getParameter("usuario");
		String contrasenia = (String) request.getParameter("contrasenia");

		try {

			Usuario usuario = usuarioDao.exist(nombre, contrasenia);

			if (usuario != null) {

				LOG.info("ususario logeado " + usuario);
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogeado", usuario);
				session.setMaxInactiveInterval(60 * 5);
				

				if (usuario.getRol().getId() == Rol.ROL_ADMIN) {
					// accedemos backoffice
					view = "seguridad/index.jsp";
				} else {
					// accedemos frontoffice
					view = "mipanel/index.jsp";
				}

			} else {

				request.setAttribute("mensajeAlerta",
						new Alerta(Alerta.TIPO_DANGER, "Credenciales incorrectas, prueba de nuevo"));

			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {

			request.getRequestDispatcher(view).forward(request, response);
		}

	}//doPost

}//LoginController
