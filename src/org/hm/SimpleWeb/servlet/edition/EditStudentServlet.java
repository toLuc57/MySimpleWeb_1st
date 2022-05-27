package org.hm.SimpleWeb.servlet.edition;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Student;
import org.hm.SimpleWeb.utils.StudentDBUtils;

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("id");

		Student editRow = null;
		String errorString = null;
		
		try {
			editRow = StudentDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		String birthdaySTR = editRow.getBirthday().toString();
		String[] spiltBirthday = birthdaySTR.split("-",3);

		request.setAttribute("errorString", errorString);
		request.setAttribute("student", editRow);
		request.setAttribute("day", spiltBirthday[2]);
		request.setAttribute("month", spiltBirthday[1]);
		request.setAttribute("year", spiltBirthday[0]);
		
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
		
		String birthday = year + "-" + month + "-" + day;		
		String errorString = null;
		Student editStudent = new Student(id,lastName,firstName,birthday,
				sex,telephone,address,idDepartment);
		if (errorString == null) {
			try {
				StudentDBUtils.update(editStudent);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			request.setAttribute("student", editStudent);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("day", day);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editStudent.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/studentList");
		}
	}

}
