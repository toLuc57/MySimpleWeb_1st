package org.hm.SimpleWeb.servlet.deletion;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;


@WebServlet("/learningOutcomes/delete")
public class DeleteLearningOutcomesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteLearningOutcomesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStudent = (String) request.getParameter("idStudent");
		String idCourse = (String) request.getParameter("idCourse");
		int numberOfTest = Integer.parseInt(request.getParameter("numberOfTest"));
		
		String errorString = null;
		try {
			LearningOutcomesDBUtils.delete(idStudent,idCourse,numberOfTest);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		} 
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/information/LearningOutcomesListView.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/learningOutcomesList");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
