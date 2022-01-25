package org.hm.SimpleWeb.edition;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.utils.TeacherDBUtils;


@WebServlet("/editTeacher")
public class EditTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditTeacherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String code = (String) request.getParameter("id");

		Teacher editRow = null;

		String errorString = null;

		try {
			editRow = TeacherDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow == null) {
			errorString = "Is null";
			System.out.println(errorString);
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("teacher", editRow);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editData/editTeacher.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");
		String hocVi = (String) request.getParameter("degree");
		String telephone = (String) request.getParameter("telephone");
		String maKhoa = (String) request.getParameter("idDepartment");
		
		Teacher editTeacher = new Teacher(id,name,hocVi,telephone,maKhoa);

		String errorString = null;

		if (errorString == null) {
			try {
				TeacherDBUtils.update(editTeacher);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("teacher", editTeacher);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editTeacher.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/teacherList");
		}
	}

}
