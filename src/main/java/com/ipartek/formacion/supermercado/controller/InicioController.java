package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.supermercado.modelo.dao.CategoriaDAO;
import com.ipartek.formacion.supermercado.modelo.dao.ProductoDAO;
import com.ipartek.formacion.supermercado.modelo.pojo.Categoria;
import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CategoriaDAO.class);
	
	private static ProductoDAO dao;
	private static CategoriaDAO daoCategoria;

	@Override
	public void init(ServletConfig config) throws ServletException {

		dao = ProductoDAO.getInstance();
		daoCategoria = CategoriaDAO.getInstance();
		super.init(config);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void destroy() {
		super.destroy();
		dao = null;
		daoCategoria = null;
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

		

		// llamar al los DAO capas modelo.
		ArrayList<Producto> productos = (ArrayList<Producto>) dao.getAll();
		request.setAttribute("productos", productos);
		
		
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) daoCategoria.getAll();
		request.setAttribute("categorias", categorias);

		request.setAttribute("mensajeAlerta", new Alerta(Alerta.TIPO_PRIMARY, "Los últimos productos destacados."));

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
