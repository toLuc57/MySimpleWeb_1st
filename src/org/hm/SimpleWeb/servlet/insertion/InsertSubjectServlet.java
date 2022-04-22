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


@WebServlet("/insertSubject")
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
		
		int numberOfPracticeLesson;
		int numberOfTheoryLesson;

		try {
			numberOfPracticeLesson = Integer.parseInt(numberOfPracticeLessonSTR);
			numberOfTheoryLesson = Integer.parseInt(numberOfTheoryLessonSTR);
		}catch (NumberFormatException e) {
			numberOfPracticeLesson = 0;
			numberOfTheoryLesson = 0;
		}
		
		Subject newRow = new Subject(name, numberOfPracticeLesson, numberOfTheoryLesson);

		String errorString = null;
		try {
			SubjectDBUtils.insert(newRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTSubject.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/subjectList");
		}
	}

}
