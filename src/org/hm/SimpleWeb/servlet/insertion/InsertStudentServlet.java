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

import org.hm.SimpleWeb.beans.Student;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;

@WebServlet("/student/insert")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = DepartmentDBUtils.getListID();
		
		request.setAttribute("departmentList", list);	
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertStudent.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String khoa = (String) request.getParameter("khoa");
		String lastName = (String) request.getParameter("lastName");
		String firstName = (String) request.getParameter("firstName");
		String day = (String) request.getParameter("day");
		String month = (String) request.getParameter("month");
		String year = (String) request.getParameter("year");
		String sex = (String) request.getParameter("sex");
		String telephone = (String) request.getParameter("telephone");
		String address = (String) request.getParameter("address");
		String idDepartment = (String) request.getParameter("idDepartment");

		String birthday = year + "-" + month + "-" + day;
		String errorString = null;
		Student newRow = new Student(lastName,firstName,birthday,
				sex,telephone,address,idDepartment);
		try {
			StudentDBUtils.insert(newRow, khoa);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			request.setAttribute("student", newRow);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("day", day);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTStudent.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/studentList");
		}
	}

}
