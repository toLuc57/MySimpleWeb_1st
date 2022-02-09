package org.hm.SimpleWeb.insertion;

import java.io.IOException;
import java.sql.Connection;
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
import org.hm.SimpleWeb.beans.Course;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;
import org.hm.SimpleWeb.utils.CourseDBUtils;

@WebServlet("/insertLearningOutcomes")
public class InsertLearningOutcomesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertLearningOutcomesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String errorString = null;
		List<LearningOutcomes> list1 = null;
		List<Student> list2 = null;
		try {
			list1 = LearningOutcomesDBUtils.query(conn);
			list2 = StudentDBUtils.query(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("courseList", list1);	
		request.setAttribute("studentList", list2);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertLearningOutcomes.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStudent = (String) request.getParameter("idStudent");
		String idCourse = (String) request.getParameter("idCourse");
		String numberOfTestSTR = (String) request.getParameter("numberOfTest");
		String pointSTR = (String) request.getParameter("point");
		
		LearningOutcomes newRow;
		
		String errorString = null;
		Course findCourse = null;
		Student findStudent = null;
		try {
			findCourse = CourseDBUtils.find(idCourse);
			findStudent = StudentDBUtils.find(idStudent);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (errorString == null && findCourse != null && findStudent != null) {
			int numberOfTest;
			double point;
			try {
				numberOfTest = Integer.parseInt(numberOfTestSTR);
				point = Double.parseDouble(pointSTR);
			} catch(NumberFormatException e) {
				numberOfTest = -1;
				point = -1;
			}
			try {
				newRow = new LearningOutcomes(idStudent,idCourse,numberOfTest,point);
				LearningOutcomesDBUtils.insert(newRow);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertLearningOutcomes.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/learningOutcomesList");
		}
	}

}
