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

import org.hm.SimpleWeb.beans.Department;
import org.hm.SimpleWeb.module.SearchModule;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;


@WebServlet("/departmentList")
public class DepartmentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DepartmentListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryWhere = SearchModule.getSQLWhere(request, DepartmentDBUtils.className);
		Map<String,String> mapColumn = DepartmentDBUtils.getAllColumnNameAndTypeName();
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
		List<Department> list = null;
		List<String> listColumnName = null;
		int totalRow = 0;
		try 
		{
			list = DepartmentDBUtils.query(conn,indexPage,queryWhere);	
			totalRow = DepartmentDBUtils.getTotalRow(queryWhere);
			listColumnName = DepartmentDBUtils.getColumnName();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("departmentList", list);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("page", indexPage);
		request.setAttribute("listColumnName", listColumnName);
		request.setAttribute("mapColumn", mapColumn);
				
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/DepartmentListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
