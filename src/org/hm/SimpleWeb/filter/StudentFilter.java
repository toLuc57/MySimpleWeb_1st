package org.hm.SimpleWeb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.MyUtils;

/**
 * Servlet Filter implementation class StudentFilter
 */
@WebFilter(urlPatterns = {"/CourseRegistration"})
public class StudentFilter implements Filter {

    public StudentFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
		if(loginedUser == null) {
			RequestDispatcher dispatcher = request.getServletContext().
					getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(loginedUser.getIsStudent()) {
			request.setAttribute("idStudent", loginedUser.getID());
			chain.doFilter(request, response);
			return;
		} else {
			RequestDispatcher dispatcher = request.getServletContext().
					getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
