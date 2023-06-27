package net.etfbl.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.etfbl.ip.beans.CategoryBean;
import net.etfbl.ip.dto.Category;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/editCategory")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
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
			String editName = request.getParameter("editName");
		
			CategoryBean categoryBean = new CategoryBean();
			categoryBean.updateCategory(new Category(editId, editName));
		
		}
		String address = "WEB-INF/pages/content/editCategory.jsp";
		System.out.println("OBradi updejt");
		if (action == null || "".equals(action)) {
			response.sendRedirect("/admin/?action=kategorija");
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
