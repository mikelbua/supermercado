package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String USUARIO = "admin";
    private static final String CONTRASENIA ="admin";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//parametros del form
		String view = "login.jsp";
		String nombre = (String)request.getParameter("usuario");
		String pass = (String)request.getParameter("contrasenia");
		try {
			if(USUARIO.equals(nombre) && CONTRASENIA.equalsIgnoreCase(pass)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogeado", nombre);
				session.setMaxInactiveInterval(60*5);
				
				view = "seguridad/index.jsp";
				
			} else {
				request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_DANGER,"Sus credenciales no son correctos!"));
			}
		} catch (Exception e) {
			// TODO:traza log
			e.printStackTrace();
			
		} finally {
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		
	}

}
