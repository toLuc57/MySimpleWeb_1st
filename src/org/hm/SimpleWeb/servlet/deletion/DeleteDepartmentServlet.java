package org.hm.SimpleWeb.servlet.deletion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.utils.DepartmentDBUtils;


@WebServlet("/department/delete")
public class DeleteDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteDepartmentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("id");

		String errorString = null;
		try {
			errorString = DepartmentDBUtils.delete(code);
		} catch (SQLException e) {
			//e.printStackTrace();
			errorString = e.getMessage();			
		} 
		if (errorString != null) {
			request.getSession().setAttribute("errorString", errorString);
		}
		response.sendRedirect(request.getContextPath() + "/departmentList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
