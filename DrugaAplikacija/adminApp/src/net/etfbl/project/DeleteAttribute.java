package net.etfbl.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.ip.beans.*;
import net.etfbl.ip.dto.*;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/deleteAttribute")
public class DeleteAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAttribute() {
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
		String action = request.getParameter("id");
		
		if (action != null) {
			Integer id = Integer.parseInt(action);
			AttributeBean attributeBean = new AttributeBean();
			attributeBean.deleteAttribute(new Attribute(id, (byte)1));
		}
//		System.out.println("DELETE: " + action);
		
		response.sendRedirect("/admin/attributes?id="+categoryId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
