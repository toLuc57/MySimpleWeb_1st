package org.hm.SimpleWeb.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hm.SimpleWeb.beans.UserAccount;


public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION"; ;
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	public static void storeConnection(ServletRequest request,Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION,conn);
	}
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
	
	public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}
	public static UserAccount getLoginedUser(HttpSession session) {
		return (UserAccount) session.getAttribute("loginedUser");
	}
	
	public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
		// don vi la giay(s), 3*60 la 3 phut
		cookieUserName.setMaxAge(3*60);
		response.addCookie(cookieUserName);
	}
	public static String getUserNameCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(ATT_NAME_USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME,null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
}
