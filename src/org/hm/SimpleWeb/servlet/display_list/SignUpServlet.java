package org.hm.SimpleWeb.servlet.display_list;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.UserAccountDBUtils;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/views/signupView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String id =  request.getParameter("id");
		UserAccountDBUtils.insert(new UserAccount(userName, password, id));
		response.sendRedirect(request.getContextPath() + "/");
	}

}
