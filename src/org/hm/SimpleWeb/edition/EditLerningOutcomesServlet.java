package org.hm.SimpleWeb.edition;

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
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;

@WebServlet("/editLearningOutcomes")
public class EditLerningOutcomesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditLerningOutcomesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String code = (String) request.getParameter("id");

		LearningOutcomes editRow = null;
		List<Student> list1 = null;
		List<LearningOutcomes> list2 = null;

		String errorString = null;

		try {
			editRow = LearningOutcomesDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow == null) {
			System.out.println(errorString);
		}
		else {			
			try 
			{
				list1 = StudentDBUtils.query(conn);		
				list2 = LearningOutcomesDBUtils.query(conn);
			} 
			catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
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
		String numberOfTestSTR = (String) request.getParameter("numberOfTest");
		String pointSTR = (String) request.getParameter("point");
		int numberOfTest;
		int point;
		try {
			numberOfTest = Integer.valueOf(numberOfTestSTR);
			point = Integer.valueOf(pointSTR);
		}catch(NumberFormatException e) {
			numberOfTest = 0;
			point = 0;
		}
				
		LearningOutcomes editRow = new LearningOutcomes(idCourse,idStudent,numberOfTest,point);

		String errorString = null;

		if (errorString == null) {
			try {
				LearningOutcomesDBUtils.update(editRow);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
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