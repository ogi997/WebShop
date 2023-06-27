package net.etfbl.project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.etfbl.ip.beans.*;
import net.etfbl.ip.dto.*;
/**
 * Servlet implementation class EditAttribute
 */
@WebServlet("/editAttribute")
public class EditAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAttribute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String categoryId = null;
		if (session.getAttribute("categoryId") != null)
			categoryId = session.getAttribute("categoryId").toString();
		//System.out.println("edit attribute for category ID: "+ categoryId);
		
		String action = request.getParameter("id");
		//System.out.printf("edit attribute action: "+ action);
		String address = "WEB-INF/pages/content/editAttribute.jsp";

		if (action == null) {
		//updejtuj korisnika
			Integer editId = Integer.parseInt(request.getParameter("hiddenId"));
			String editName = request.getParameter("editName");
		
			AttributeBean attributeBean = new AttributeBean();
			attributeBean.updateAttribute(new Attribute(editId, editName));
		
		}
		if (action == null || "".equals(action)) {
			response.sendRedirect("/admin/attributes?id="+categoryId);
			return;
//			address = "WEB-INF/pages/content/korisnici.jsp";
		}
		
		RequestDispatcher dispatcer = request.getRequestDispatcher(address);
		dispatcer.forward(request, response);
		
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
