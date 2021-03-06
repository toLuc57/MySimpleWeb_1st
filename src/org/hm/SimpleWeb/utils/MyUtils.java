package org.hm.SimpleWeb.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hm.SimpleWeb.beans.UserAccount;


public class MyUtils {
	public static final int numberInID = 4;
	public static final String LOG_OUT = "LOG_OUT";
	
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	private static final String ATT_NAME_LOG_OUT = "ATTRIBUTE_FOR_STORE_LOG_OUT_IN_COOKIE";
	
	private static int REDIRECT_ID = 0;

	private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
	private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
	
	public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}
	public static UserAccount getLoginedUser(HttpSession session) {
		return (UserAccount) session.getAttribute("loginedUser");
	}
	public static void deleteLoginedUser(HttpSession session) {
		session.setAttribute("loginedUser", null);
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
	
	public static void storeLogOutCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_LOG_OUT, LOG_OUT);
		// don vi la giay(s), 3*60 la 3 phut
		cookieUserName.setMaxAge(3*60);
		response.addCookie(cookieUserName);
	}
	public static String getLogOutCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(ATT_NAME_LOG_OUT.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	public static void deleteLogOutCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_LOG_OUT,null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
	public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
		Integer id = uri_id_map.get(requestUri);

		if (id == null) {
			id = REDIRECT_ID++;

			uri_id_map.put(requestUri, id);
			id_uri_map.put(id, requestUri);
			return id;
		}

		return id;
	}

	public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
		String url = id_uri_map.get(redirectId);
		if (url != null) {
			return url;
		}
		return null;
	}
}
