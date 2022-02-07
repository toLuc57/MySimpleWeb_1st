package org.hm.SimpleWeb.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.UserAccountDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;

@WebFilter(filterName="cookieFilter", urlPatterns= {"/*"})
public class CookieFilter implements Filter {

    public CookieFilter() {
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		UserAccount userInSession = MyUtils.getLoginedUser(session);
		
		if(userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			System.out.println("Account: " + userInSession.getUserName());
			chain.doFilter(request, response);
			return;
		}
		System.out.println("Account is null");
		Connection conn = MyUtils.getStoredConnection(request);
		
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if(checked == null && conn !=null) {
			String userName = MyUtils.getUserNameCookie(req);
			try {
				UserAccount user = UserAccountDBUtils.find(conn, userName);
				MyUtils.storeLoginedUser(session, user);
				if(user== null) {
					System.out.println("Store Logined User(null)");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
