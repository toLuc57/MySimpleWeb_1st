package org.hm.SimpleWeb.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.hm.SimpleWeb.jdbc.MySQLConnUtils;
import org.hm.SimpleWeb.utils.MyUtils;


@WebFilter(filterName="JDBCFilter", urlPatterns= {"/*"})
public class JDBCFilter implements Filter {
 
    public JDBCFilter() {
    }
    @Override
	public void destroy() {
	}
	private boolean needJDBC(HttpServletRequest request) {
		System.out.println("JDBC Filter");
		
		String servletPath = request.getServletPath();
		
		String pathInfo = request.getPathInfo();

		String urlPattern = servletPath;

		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
		}
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();
		
		// Tap hop tat ca cac Servlet trong WebApp.
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(this.needJDBC(req)) {
			System.out.println("Open Connection for: " + req.getServletPath());
			Connection conn = null;
			try {
				conn = MySQLConnUtils.getMySQLConUtils();
				conn.setAutoCommit(false);
				
				MyUtils.storeConnection(request, conn);
				chain.doFilter(request, response);
			} catch(Exception e) {
				e.printStackTrace();
				MySQLConnUtils.rollbackQuietly(conn);
				throw new ServletException();
			} finally {
				MySQLConnUtils.closeQuietly(conn);
				System.out.println("Closed connection...");
			}
		}
		else {
			chain.doFilter(request, response);
		}
		
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
