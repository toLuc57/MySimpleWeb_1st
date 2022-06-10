package org.hm.SimpleWeb.servlet.display_list;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.LearningOutcomes;
import org.hm.SimpleWeb.jdbc.MySQLConnUtils;
import org.hm.SimpleWeb.module.SearchModule;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;


@WebServlet("/learningOutcomesList")
public class LearningOutcomesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LearningOutcomesListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryWhere = SearchModule.getSQLWhere(request, LearningOutcomesDBUtils.className);
		if(queryWhere.equals(" ") && request.getParameter("search") != null) {
			queryWhere = LearningOutcomesDBUtils.getQueryWhereSearchIDAndName(
					request.getParameter("search"));
		}
		Map<String,String> mapColumn = LearningOutcomesDBUtils.getAllColumnNameAndTypeName();
		
		String indexPageSTR = request.getParameter("page");
		int indexPage = 0;
		if(indexPageSTR != null) {
			try {
				indexPage = Integer.parseInt(indexPageSTR);
			}catch(NumberFormatException e) {
				indexPage = 0;
			}
			
		}
		String errorString = null;	
		List<LearningOutcomes> list = null;
		List<String> listColumnName = null;
		int totalRow = 0;
		try 
		{
			Connection conn = MySQLConnUtils.getMySQLConUtils();
			list = LearningOutcomesDBUtils.query(conn,indexPage, queryWhere);
			totalRow = LearningOutcomesDBUtils.getTotalRow(queryWhere);
			listColumnName = LearningOutcomesDBUtils.getColumnName();
			conn.close();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if(request.getSession().getAttribute("errorString") != null) {
			if(errorString == null) {
				
				errorString = String.valueOf(request.getSession().getAttribute("errorString"));
			}
			else {
				errorString = errorString + "\n" 
						+ String.valueOf(request.getSession().getAttribute("errorString"));
			}
			request.getSession().setAttribute("errorString", null);
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("learningOutcomesList", list);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("page", indexPage);
		request.setAttribute("listColumnName", listColumnName);
		request.setAttribute("mapColumn", mapColumn);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/LearningOutcomesListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
