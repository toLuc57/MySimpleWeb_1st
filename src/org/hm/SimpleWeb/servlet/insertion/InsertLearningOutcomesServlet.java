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

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;
import org.hm.SimpleWeb.utils.CourseDBUtils;

@WebServlet("/learningOutcomes/insert")
public class InsertLearningOutcomesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertLearningOutcomesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list1 = CourseDBUtils.getListID();
		List<String> list2 = StudentDBUtils.getListID();
		
		request.setAttribute("courseList", list1);	
		request.setAttribute("studentList", list2);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertLearningOutcomes.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStudent = (String) request.getParameter("idStudent");
		String idCourse = (String) request.getParameter("idCourse");
		String pointSTR = (String) request.getParameter("point");
		
		LearningOutcomes newRow = null;
		int numberOfTest = 0;
		String errorString = "";
		try {
			List<LearningOutcomes> findRows = LearningOutcomesDBUtils.find(idStudent, idCourse);
			if(findRows != null)
			numberOfTest = findRows.size() + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = errorString.concat(e.getMessage());
		}
		
		double point;
		try {
			point = Double.parseDouble(pointSTR);
		} catch(NumberFormatException e) {
			point = -1;
			errorString = errorString.concat("<br/>" + e.getMessage());
		}
		
		try {
			newRow = new LearningOutcomes(idStudent,idCourse,numberOfTest,point);
			LearningOutcomesDBUtils.insert(newRow);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = errorString.concat("<br/>" + e.getMessage());
		}
		
		if (errorString != null) {
			request.setAttribute("learningOutcomes", newRow);
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertLearningOutcomes.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/learningOutcomesList");
		}
	}

}
