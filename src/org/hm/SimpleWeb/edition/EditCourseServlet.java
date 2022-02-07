package org.hm.SimpleWeb.edition;

import java.io.IOException;
import java.sql.Connection;
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
import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.utils.CourseDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;

@WebServlet("/editCourse")
public class EditCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCourseServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String code = (String) request.getParameter("id");

		Course editRow = null;
		List<Subject> list1 = null;
		List<Teacher> list2 = null;

		String errorString = null;

		try {
			editRow = CourseDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow == null) {
			System.out.println(errorString);
		}
		else {
			String fromDateSTR = editRow.getFromDate().toString();
			String[] spiltFromDate = fromDateSTR.split("-",3);
			String toDateSTR = editRow.getToDate().toString();
			String[] spiltToDate = toDateSTR.split("-",3);
			
			request.setAttribute("fromDay", spiltFromDate[0]);	
			request.setAttribute("fromMonth", spiltFromDate[1]);	
			request.setAttribute("fromYear", spiltFromDate[2]);	
			
			request.setAttribute("toDay", spiltToDate[0]);	
			request.setAttribute("toMonth", spiltToDate[1]);	
			request.setAttribute("toYear", spiltToDate[2]);	
			try 
			{
				list1 = SubjectDBUtils.query(conn);		
				list2 = TeacherDBUtils.query(conn);
			} 
			catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		request.setAttribute("teacherList", list2);	
		request.setAttribute("subjectsList", list1);
		request.setAttribute("errorString", errorString);
		request.setAttribute("course", editRow);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editData/editCourse.jsp");
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
		
		Course editRow = new Course(id,idTeacher,idSubject,fromDate,toDate);

		String errorString = null;

		if (errorString == null) {
			try {
				CourseDBUtils.update(editRow);
				
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("course", editRow);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editData/editCourse.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/courseList");
		}
	}

}
