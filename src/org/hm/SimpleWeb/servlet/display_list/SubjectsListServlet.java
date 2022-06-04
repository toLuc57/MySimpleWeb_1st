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

import org.hm.SimpleWeb.beans.Subject;
import org.hm.SimpleWeb.module.SearchModule;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;

@WebServlet("/subjectList")
public class SubjectsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SubjectsListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryWhere = SearchModule.getSQLWhere(request, SubjectDBUtils.className);
		if(queryWhere.equals(" ") && request.getParameter("search") != null) {
			queryWhere = SubjectDBUtils.getQueryWhereSearchIDAndName(
					request.getParameter("search"));
		}
		Map<String,String> mapColumn = SubjectDBUtils.getAllColumnNameAndTypeName();
		Connection conn = MyUtils.getStoredConnection(request);
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
		List<Subject> list = null;
		List<String> listColumnName = null;
		int totalRow = 0;
		try 
		{
			list = SubjectDBUtils.query(conn,indexPage,queryWhere);
			totalRow = SubjectDBUtils.getTotalRow(queryWhere);
			listColumnName = SubjectDBUtils.getColumnName();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("subjectsList", list);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("page", indexPage);
		request.setAttribute("listColumnName", listColumnName);
		request.setAttribute("mapColumn", mapColumn);
				
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/SubjectListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
