package org.hm.SimpleWeb.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Department;
import org.hm.SimpleWeb.beans.Student;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;

@WebServlet("/insertStudent")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertStudentServlet() {
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
				.getRequestDispatcher("/WEB-INF/views/insertData/insertStudent.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastName = (String) request.getParameter("lastName");
		String firstName = (String) request.getParameter("firstName");
		String day = (String) request.getParameter("day");
		String month = (String) request.getParameter("month");
		String year = (String) request.getParameter("year");
		String sex = (String) request.getParameter("sex");
		String telephone = (String) request.getParameter("telephone");
		String address = (String) request.getParameter("address");
		String idDepartment = (String) request.getParameter("idDepartment");

		Date birthday = Date.valueOf(year + "-" + month + "-" + day);
		
		System.out.println("Birthday: " + birthday.toString());
		System.out.println("Sex: " + sex);
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
				Student newRow = new Student(lastName,firstName,birthday,
						sex,telephone,address,idDepartment);
				StudentDBUtils.insert(newRow);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTStudent.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/studentList");
		}
	}

}
