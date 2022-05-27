package org.hm.SimpleWeb.servlet.insertion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.utils.TeacherDBUtils;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;

@WebServlet("/teacher/insert")
public class InsertTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertTeacherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = DepartmentDBUtils.getListID();
		
		request.setAttribute("departmentList", list);	
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertTeacher.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		String degress = (String) request.getParameter("degress");
		String telephone = (String) request.getParameter("telephone");
		String idDepartment = (String) request.getParameter("idDepartment");
		
		String errorString = null;
		Teacher newTeacher = new Teacher(name,degress,telephone,idDepartment);
		
		try {
			TeacherDBUtils.insert(newTeacher);				
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
	
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			request.setAttribute("teacher", newTeacher);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTeacher.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/teacherList");
		}
	}

}
