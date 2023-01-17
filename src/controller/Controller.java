package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.DAOCoche;
import daos.DAOMarca;
import model.Coche;
import model.Marca;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String op = request.getParameter("op"); 
		RequestDispatcher dispatcher;
		
		if (op.equals("inicio")) {
			
			//Subimos datos a la sesión (desde los daos)
			//('session' guarda los datos de manera permanente)
			ArrayList<Coche> coches = new DAOCoche().getCoches("", "");
			session.setAttribute("coches", coches);
			
			ArrayList<Marca> marcas = new DAOMarca().getMarcas();
			session.setAttribute("marcas", marcas);
			
			session.setAttribute("marca", "");
			session.setAttribute("search", "");
			session.setAttribute("type", 0);
			
			//Refrescamos la vista (la página) con los nuevos datos de la sesión
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);	
			
		} else if (op.equals("getcochesbymarca")) {
			
			//Subimos datos a la sesión (desde los daos)
			//('session' guarda los datos de manera permanente)
			String marca = request.getParameter("marca");
			ArrayList<Coche> coches = new DAOCoche().getCochesByMarca(marca, "");
			
			session.setAttribute("coches", coches);
			session.setAttribute("marca", marca);
			session.setAttribute("type", 1);
			
			//Refrescamos la vista (la página) con los nuevos datos de la sesión
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			
		} else if (op.equals("getcoches")) {
			
			String search = request.getParameter("search");

			ArrayList<Coche> coches = new DAOCoche().getCoches(search, "");
			
			session.setAttribute("coches", coches);
			session.setAttribute("search", search);
			session.setAttribute("type", 0);
			
			//Refrescamos la vista (la página) con los nuevos datos de la sesión
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			
		} else if (op.equals("ordercoches")) {
			
			//Subimos datos a la sesión (desde los daos)
			//('session' guarda los datos de manera permanente)
			String marca = (String) session.getAttribute("marca");
			String search = (String) session.getAttribute("search");
			String orden = request.getParameter("orden");
			int type = (int) session.getAttribute("type");
			
			ArrayList<Coche> coches = new ArrayList<Coche>();	
			
			if (type == 0) {
				coches = new DAOCoche().getCoches(search, orden);
			} else {
				coches = new DAOCoche().getCochesByMarca(marca, orden);
			}
			
			session.setAttribute("coches", coches);			
			
			//Refrescamos la vista (la página) con los nuevos datos de la sesión
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
			
		} else if (op.equals("updatefav")) {
			
			int fav = Integer.valueOf(request.getParameter("fav"));
			int id = Integer.valueOf(request.getParameter("id"));
			new DAOCoche().updateFav(fav, id);
			
			ArrayList<Coche> coches = new DAOCoche().getCoches("", "");
			session.setAttribute("coches", coches);
			
			//Refrescamos la vista (la página) con los nuevos datos de la sesión
			dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
