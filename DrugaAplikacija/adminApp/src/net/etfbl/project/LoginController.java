package net.etfbl.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.ip.dao.UserDAO;
import net.etfbl.ip.dto.Admin;
import net.etfbl.ip.dto.User;
import net.etfbl.ip.beans.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String address = "WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");
		System.out.println("Action: " + action);
		AdminBean adminBean = new AdminBean();
		if (action == null || "".equals(action)) {
			address = "WEB-INF/pages/login.jsp";
		} else if ("login".equals(action)) {
			//provjera login
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Admin admin = adminBean.getAdminByUsernameAndPassword(username, password);
			if (admin != null) {
//				adminBean.setLogged(true);
				admin.setLogged(true);
				session.setAttribute("admin", admin);
				address = "WEB-INF/pages/content/dashboard.jsp";
//				response.sendRedirect("/dashboard");
				//uspjesna prijava
				//session.setAttribute("notification", "Uspjesno ste se prijavili na sistem");
			} else {
				session.setAttribute("notification", "Korisnicko ime ili lozinka nije dobri.");
			}
		} else if ("kategorija".equals(action) || "korisnici".equals(action) || "home".equals(action)) {
			address = "WEB-INF/pages/content/dashboard.jsp";
		} else if ("logout".equals(action)) {
//			session.invalidate();
			address = "WEB-INF/pages/login.jsp";
//			session.setAttribute("notification", "");
		} else if ("statistika".equals(action)) {
			address = "WEB-INF/pages/content/statistika.jsp";
		} else if ("editUser".equals(action)) {
			System.out.println("edit korisnika");
			address = "WEB-INF/pages/content/editUser.jsp";
			//response.sendRedirect("?action=korisnici");
		} else if ("logout.jsp".equals(action)) {
			address = "WEB-INF/pages/logout.jsp";
		}
		else {
			address = "WEB-INF/pages/404.jsp";
		}
		System.out.println("Address: " + address);
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
