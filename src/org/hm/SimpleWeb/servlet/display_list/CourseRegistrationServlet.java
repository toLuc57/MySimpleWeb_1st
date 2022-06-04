package org.hm.SimpleWeb.servlet.display_list;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.utils.SubjectOfStudentDBUtils;

@WebServlet("/CourseRegistration")
public class CourseRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CourseRegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStudent = request.getParameter("idStudent");
		
		if(idStudent != null) {
			List<Subject> list = SubjectOfStudentDBUtils.query(idStudent);
			request.setAttribute("subjectsList", list);
		}
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/SubjectListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
