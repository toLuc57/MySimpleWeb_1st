package org.hm.SimpleWeb.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.beans.Department;
import org.hm.SimpleWeb.utils.TeacherDBUtils;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;

@WebServlet("/insertTeacher")
public class InsertTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertTeacherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String errorString = null;
		List<Department> list = null;
		try {
			list = DepartmentDBUtils.query(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
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
		Department findDepartment = null;
		try {
			findDepartment = DepartmentDBUtils.find(idDepartment);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (errorString == null && findDepartment != null) {
			try {
				Teacher newTeacher = new Teacher(name,degress,telephone,idDepartment);
				TeacherDBUtils.insert(newTeacher);				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTeacher.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/teacherList");
		}
	}

}
