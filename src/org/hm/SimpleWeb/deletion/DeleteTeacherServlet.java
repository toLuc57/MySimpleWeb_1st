package org.hm.SimpleWeb.deletion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.utils.TeacherDBUtils;

@WebServlet("/deleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteTeacherServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = (String) request.getParameter("id");

		String errorString = null;
		try {
			TeacherDBUtils.delete(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		} 
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/information/TeacherListView.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/teacherList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
