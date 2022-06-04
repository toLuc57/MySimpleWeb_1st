package org.hm.SimpleWeb.servlet.edition;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.beans.Student;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;

@WebServlet("/learningOutcomes/edit")
public class EditLerningOutcomesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditLerningOutcomesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStudent = (String) request.getParameter("idStudent");
		String idCourse = (String) request.getParameter("idCourse");
		int numberOfTest = Integer.parseInt(request.getParameter("numberOfTest"));

		LearningOutcomes editRow;
		List<Student> list1 = null;
		List<LearningOutcomes> list2 = null;

		String errorString = null;

		try {
			editRow = LearningOutcomesDBUtils.find(idStudent,idCourse,numberOfTest);
		} catch (SQLException e) {
			editRow = null;
			e.printStackTrace();
			errorString = e.getMessage();			
		}
		request.setAttribute("teacherList", list2);	
		request.setAttribute("subjectsList", list1);
		request.setAttribute("errorString", errorString);
		request.setAttribute("learningOutcomes", editRow);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editData/editLearningOutcomes.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCourse = (String) request.getParameter("idCourse");
		String idStudent = (String) request.getParameter("idStudent");
		int numberOfTest = Integer.valueOf(request.getParameter("numberOfTest"));
		double point =  Double.valueOf(request.getParameter("point"));
	
		LearningOutcomes editRow = new LearningOutcomes(idStudent,idCourse,numberOfTest,point);
		String errorString = null;
		try {
			LearningOutcomesDBUtils.update(editRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("learningOutcomes", editRow);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editLearningOutcomes.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/learningOutcomesList");
		}
	}

}
