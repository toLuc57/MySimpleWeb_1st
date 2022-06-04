package org.hm.SimpleWeb.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import org.hm.SimpleWeb.request.UserRoleRequestWrapper;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.SecurityUtils;
import org.hm.SimpleWeb.utils.UserAccountDBUtils;

@WebFilter(filterName="SecurityFilter", urlPatterns= {"/*"})
public class SecurityFilter implements Filter {


    public SecurityFilter() {
    }
    @Override
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//System.out.print("SecurityFilter: ");
		String servletPath = request.getServletPath();		
		
		// Thông tin người dùng đã được lưu trong Session
		// (Sau khi đăng nhập thành công).
		UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
		
		if (servletPath.equals("/login")) {
			if(loginedUser != null) {
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}
			String username = MyUtils.getUserNameCookie(request);
			if(username != null) {
				try {
					loginedUser = UserAccountDBUtils.find(username);
					if (loginedUser != null) {
						if (MyUtils.getLogOutCookie(request) == null) {
							MyUtils.storeLoginedUser(request.getSession(), loginedUser);
							response.sendRedirect(request.getContextPath() + "/");
							return;
						}
						else {
							request.setAttribute("user", loginedUser);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();					
				}
			}
			chain.doFilter(request, response);
			return;
		}
		// Nếu trong session không có 
		// (trong trường hợp người dùng chưa logout) thì
		// kiểm tra xem tài khoản người dùng 
		// có được lưu ở cookie không (trang web khác /login)
		if (MyUtils.getLogOutCookie(request) == null && loginedUser == null) {
			String username = MyUtils.getUserNameCookie(request);
			if(username != null) {
				try {
					loginedUser = UserAccountDBUtils.find(username);
					MyUtils.storeLoginedUser(request.getSession(), loginedUser);
				} catch (SQLException e) {
					e.printStackTrace();					
				}
			}
		}			
		
		HttpServletRequest wrapRequest = request;

		if (loginedUser != null) {
			// User Name
			String userName = loginedUser.getUserName();
			// Các vai trò (Role).
			List<String> roles = loginedUser.getRoles();
			// Gói request cũ bởi một Request mới với các thông tin userName và Roles.
			wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
		}
		//System.out.println(SecurityUtils.isSecurityPage(request));
		// Các trang bắt buộc phải đăng nhập.
		if (SecurityUtils.isSecurityPage(request)) {
				if(loginedUser == null) {
					String requestUri = request.getRequestURI();
					// Lưu trữ trang hiện tại để redirect đến sau khi đăng nhập thành công.
					int redirectId = 
							MyUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
					response.sendRedirect(wrapRequest.getContextPath() 
							+ "/login?redirectId=" + redirectId);
					return;
				}	
				// Kiểm tra người dùng có vai trò hợp lệ hay không?
				boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
				//System.out.println("    Has Permission:"+ hasPermission);
				if (!hasPermission) {
					RequestDispatcher dispatcher = request.getServletContext().
							getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

					dispatcher.forward(request, response);
					return;
				}
		}
		chain.doFilter(wrapRequest, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
