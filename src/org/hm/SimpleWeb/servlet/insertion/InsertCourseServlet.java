package org.hm.SimpleWeb.servlet.insertion;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.Course;
import org.hm.SimpleWeb.utils.CourseDBUtils;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;


@WebServlet("/course/insert")
public class InsertCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertCourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list1 = SubjectDBUtils.getListIDs();
		List<String> list2 = TeacherDBUtils.getListIDs();
		
		request.setAttribute("teacherList", list2);	
		request.setAttribute("subjectsList", list1);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertData/insertCourse.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String idSubject = (String) request.getParameter("idSubject");
		String idTeacher = (String) request.getParameter("idTeacher");
		String fromDay = (String) request.getParameter("fromDay");
		String fromMonth = (String) request.getParameter("fromMonth");
		String fromYear = (String) request.getParameter("fromYear");
		String toDay = (String) request.getParameter("toDay");
		String toMonth = (String) request.getParameter("toMonth");
		String toYear = (String) request.getParameter("toYear");
		
		Date fromDate = Date.valueOf(fromYear + "-" + fromMonth + "-" + fromDay);
		Date toDate = Date.valueOf(toYear + "-" + toMonth + "-" + toDay);
		
		Course newRow = new Course(id,idTeacher,idSubject,fromDate,toDate);

		String errorString = null;
		try {
			CourseDBUtils.insert(newRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/insertData/insertTCourse.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/courseList");
		}
	}

}
