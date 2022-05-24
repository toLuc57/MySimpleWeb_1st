package org.hm.SimpleWeb.servlet.display_list;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.UserAccountDBUtils;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int redirectId = -1;
		try {
			redirectId = Integer.parseInt(request.getParameter("redirectId"));
		} catch (Exception e) {
		}
		
		request.setAttribute("redirectId",redirectId);
		
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);
		
		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} 
		else {
			try {
				user = UserAccountDBUtils.find(userName, password);
				if (user == null) {
					hasError = true;
					errorString = "User Name or password not correct";
				} /*
				else {
					System.out.println("-----Information for user-----");
					System.out.println(user.getUserName());
					System.out.println("Student? " + user.isStudent());
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}*/
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		
		if (!hasError) {
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);

			if (remember) {
				MyUtils.storeUserCookie(response, user);
			}
			else {
				MyUtils.deleteUserCookie(response);
			}
			
			int redirectId = -1;
			try {
				redirectId = Integer.parseInt(request.getParameter("redirectId"));
			} catch (Exception e) {
			}
			//System.out.println("  Redirect Id: " + redirectId);
			String requestUri = MyUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
			//System.out.println("  Request Uri: " + requestUri);
			if (requestUri != null) {
				response.sendRedirect(requestUri);
			} else {
				// Mặc định (tức là trang trước đó không lưu) 
				// sau khi đăng nhập thành công
				// chuyển hướng về trang /userinfo
				response.sendRedirect(request.getContextPath() + "/userInfo");
			}
		}
		else {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
	}

}
