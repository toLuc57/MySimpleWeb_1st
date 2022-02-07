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

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;


@WebServlet("/learningOutcomesList")
public class LearningOutcomesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LearningOutcomesListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;	
		List<LearningOutcomes> list = null;
		try 
		{
			list = LearningOutcomesDBUtils.query(conn);			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("learningOutcomesList", list);
				
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/LearningOutcomesListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
