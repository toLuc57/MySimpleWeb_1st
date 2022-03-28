package org.hm.SimpleWeb.servlet;

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

import org.hm.SimpleWeb.beans.Teacher;
import org.hm.SimpleWeb.utils.TeacherDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;


@WebServlet("/teacherList")
public class TeacherListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TeacherListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		String indexPageSTR = request.getParameter("inputPage");
		if(indexPageSTR == null) {
			indexPageSTR = request.getParameter("page");
		}
		int indexPage = 0;
		if(indexPageSTR != null) {
			try {
				indexPage = Integer.parseInt(indexPageSTR);
			}catch(NumberFormatException e) {
				indexPage = 0;
			}
		}
		
		String errorString = null;
		List<Teacher> list = null;
		int totalRow = 0;
		try {
			list = TeacherDBUtils.query(conn,indexPage);
			totalRow = TeacherDBUtils.getTotalRow();
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("teacherList", list);		
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("page", indexPage);
		
		System.out.println("===================================");
		for(Teacher i : list) {
			System.out.println(i.getId() + " " + i.getName());
		}
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/TeacherListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
