package org.hm.SimpleWeb.servlet.insertion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.utils.SubjectDBUtils;


@WebServlet("/subject/insert")
public class InsertSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertSubjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertSubject.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter("name");
		String numberOfPracticeLessonSTR = (String) request.getParameter("numberOfPracticeLesson");
		String numberOfTheoryLessonSTR = (String) request.getParameter("numberOfTheoryLesson");
		
		int numberOfPracticeLesson = 0;
		int numberOfTheoryLesson = 0;
		String errorString = null;
		try {
			numberOfPracticeLesson = Integer.parseInt(numberOfPracticeLessonSTR);
			numberOfTheoryLesson = Integer.parseInt(numberOfTheoryLessonSTR);
		}catch (NumberFormatException e) {
		}
		try {
			Subject newRow = new Subject(name, numberOfPracticeLesson, numberOfTheoryLesson);
			SubjectDBUtils.insert(newRow);
			
		} catch (SQLException e) {
			errorString = e.getMessage();
		}
		
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertSubject.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/subjectList");
		}
	}

}
