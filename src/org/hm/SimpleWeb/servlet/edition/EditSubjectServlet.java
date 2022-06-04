package org.hm.SimpleWeb.servlet.edition;

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


@WebServlet("/subject/edit")
public class EditSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditSubjectServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("id");

		Subject editRow = null;

		String errorString = null;

		try {
			editRow = SubjectDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow == null) {
			System.out.println(errorString);
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("subject", editRow);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editData/editSubject.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");
		String numberOfPracticeLessonSTR = (String) request.getParameter("numberOfPracticeLesson");
		String numberOfTheoryLessonSTR = (String) request.getParameter("numberOfTheoryLesson");
		int numberOfTheoryLesson;
		int numberOfPracticeLesson;
		try {
			numberOfTheoryLesson = Integer.valueOf(numberOfTheoryLessonSTR);
			numberOfPracticeLesson = Integer.valueOf(numberOfPracticeLessonSTR);
		} catch (NumberFormatException e) {
			numberOfTheoryLesson = 0;
			numberOfPracticeLesson = 0;
		}
		Subject editRow = new Subject(id,name,numberOfTheoryLesson,numberOfPracticeLesson);

		String errorString = null;

		if (errorString == null) {
			try {
				SubjectDBUtils.update(editRow);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("subject", editRow);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editSubject.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/subjectList");
		}
	}

}
