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

import org.hm.SimpleWeb.beans.Course;
import org.hm.SimpleWeb.utils.CourseDBUtils;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;

@WebServlet("/course/edit")
public class EditCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCourseServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = (String) request.getParameter("id");

		Course editRow = null;
		List<String> list1 = SubjectDBUtils.getListID();
		List<String> list2 = TeacherDBUtils.getListID();

		String errorString = null;

		try {
			editRow = CourseDBUtils.find(code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (editRow != null){
			String fromDateSTR = editRow.getFromDate().toString();
			String[] spiltFromDate = fromDateSTR.split("-",3);
			String toDateSTR = editRow.getToDate().toString();
			String[] spiltToDate = toDateSTR.split("-",3);
			
			request.setAttribute("fromDay", spiltFromDate[2]);	
			request.setAttribute("fromMonth", spiltFromDate[1]);	
			request.setAttribute("fromYear", spiltFromDate[0]);	
			
			request.setAttribute("toDay", spiltToDate[2]);	
			request.setAttribute("toMonth", spiltToDate[1]);	
			request.setAttribute("toYear", spiltToDate[0]);	
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
		
		String fromDate = fromYear + "-" + fromMonth + "-" + fromDay;
		String toDate = toYear + "-" + toMonth + "-" + toDay;
		
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
