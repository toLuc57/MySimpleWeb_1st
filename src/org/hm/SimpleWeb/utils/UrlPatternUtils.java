package org.hm.SimpleWeb.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

public class UrlPatternUtils {
	private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {

	      Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

	      for (String servletName : map.keySet()) {
	         ServletRegistration sr = map.get(servletName);

	         Collection<String> mappings = sr.getMappings();
	         if (mappings.contains(urlPattern)) {
	            return true;
	         }

	      }
	      return false;
	   }

	   // Result:
	   // ==> /spath
	   // ==> /spath/*
	   // ==> *.ext
	   // ==> /
	public static String getUrlPattern(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		/*
		System.out.println("  --servletPath: " + servletPath );
		System.out.println("  --pathInfo: " + pathInfo );
		System.out.println("  --lastIndexOf('/'): " + servletPath.lastIndexOf('/') );
		*/
		String urlPattern = null;
		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
			return urlPattern;
		}
		// Chuyển spath/insert sang spath/*
		int i = servletPath.lastIndexOf('/');
		if (i > 0) {
			urlPattern = servletPath.substring(0,i) + "/*";
			//System.out.println("  --urlPattern: " + urlPattern);
			return urlPattern;
		}
		
		urlPattern = servletPath;
		
		boolean has = hasUrlPattern(servletContext, urlPattern);
		if (has) {
			return urlPattern;
		}
		i = servletPath.lastIndexOf('.');
		if (i != -1) {
			String ext = servletPath.substring(i + 1);
			urlPattern = "*." + ext;
			has = hasUrlPattern(servletContext, urlPattern);
			
			if (has) {
				return urlPattern;
			}
		}
		return "/";
	}
}
