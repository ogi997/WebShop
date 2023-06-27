package net.etfbl.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.etfbl.ip.beans.UserBean;
import net.etfbl.ip.dto.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/editUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("id");
		if (action == null) {
		//updejtuj korisnika
			Integer editId = Integer.parseInt(request.getParameter("hiddenId"));
			String editFirstName = request.getParameter("editFirstName");
			String editLastName = request.getParameter("editLastName");
			String editUsername = request.getParameter("editUsername");
			String editPassword = request.getParameter("editPassword");
			String editCity = request.getParameter("editCity");
			String editEmail = request.getParameter("editEmail");
		
			UserBean userBean = new UserBean();
			userBean.updateUser(new User(editId, editFirstName, editLastName, editUsername, editPassword, editCity, editEmail));
		
		}
		String address = "WEB-INF/pages/content/editUser.jsp";
		System.out.println("OBradi updejt");
		if (action == null || "".equals(action)) {
			response.sendRedirect("/admin/?action=korisnici");
			return;
//			address = "WEB-INF/pages/content/korisnici.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
//		response.sendRedirect("WEB-INF/pages/content/editUser.jsp");		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
