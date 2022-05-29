package org.hm.SimpleWeb.servlet.display_list;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hm.SimpleWeb.beans.ResultOfStudentsView;
import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.MyUtils;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} 
		if(loginedUser.getIsStudent()) {
			List<ResultOfStudentsView> listInfoStudent = loginedUser.getListInfoStudent();
			request.setAttribute("listInfoStudent", listInfoStudent);
		}
		request.setAttribute("user", loginedUser);

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
