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

import org.hm.SimpleWeb.beans.GiaoVien;
import org.hm.SimpleWeb.beans.Khoa;
import org.hm.SimpleWeb.utils.GiaoVienDBUtils;
import org.hm.SimpleWeb.utils.KhoaDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;


@WebServlet("/teacherList")
public class GiaoVienListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GiaoVienListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		List<GiaoVien> list = null;
		try {
			list = GiaoVienDBUtils.query(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("teacherList", list);		
		
		System.out.println("===================================");
		for(GiaoVien i : list) {
			System.out.println(i.getId() + " " + i.getName());
		}
		System.out.println("===================================");
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/information/TeacherListView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
