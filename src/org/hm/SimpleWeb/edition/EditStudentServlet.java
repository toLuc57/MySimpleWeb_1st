package org.hm.SimpleWeb.edition;

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

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String code = (String) request.getParameter("id");

		Student editRow = null;
		List<Department> list = null;
		String errorString = null;
		
		try {
			editRow = StudentDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow == null) {
			errorString = "Is null";
			System.out.println(errorString);
		}
		else {
			try {
				list = DepartmentDBUtils.query(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}	
		}
		String birthdaySTR = editRow.getBirthday().toString();
		String[] spiltBirthday = birthdaySTR.split("-",3);

		request.setAttribute("errorString", errorString);
		request.setAttribute("student", editRow);
		request.setAttribute("day", spiltBirthday[2]);
		request.setAttribute("month", spiltBirthday[1]);
		request.setAttribute("year", spiltBirthday[0]);
		request.setAttribute("departmentList", list);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editData/editStudent.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
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
		
		Student editStudent = new Student(id,lastName,firstName,birthday,
				sex,telephone,address,idDepartment);
		
		String errorString = null;

		if (errorString == null) {
			try {
				StudentDBUtils.update(editStudent);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("student", editStudent);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editStudent.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/studentList");
		}
	}

}
