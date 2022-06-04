package org.hm.SimpleWeb.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_STUDENT = "STUDENT";
	public static final String ROLE_TEACHER = "TEACHER";

	// String: Role
	// List<String>: urlPatterns.
	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

	static {
		init();
	}

	private static void init() {
		// Configure role "STUDENT".
		List<String> urlPatterns1 = new ArrayList<String>();

		urlPatterns1.add("/userInfo");
		urlPatterns1.add("/courseList");
		urlPatterns1.add("/subjectList");

		mapConfig.put(ROLE_STUDENT, urlPatterns1);

		// Configure role "TEACHER".
		List<String> urlPatterns2 = new ArrayList<String>();

		urlPatterns2.add("/userInfo");	
		urlPatterns2.add("/learningOutcomesList");
		urlPatterns2.add("/courseList");
		urlPatterns2.add("/studentList");
		urlPatterns2.add("/subjectList");
		
		mapConfig.put(ROLE_TEACHER, urlPatterns2);
		
		// Configure role "ADMIN".
		List<String> urlPatterns3 = new ArrayList<String>();

		urlPatterns3.add("/userInfo");
		
		urlPatterns3.add("/teacher/*");
		
		urlPatterns3.add("/learningOutcomesList");
		urlPatterns3.add("/learningOutcomes/*");
		
		urlPatterns3.add("/courseList");
		urlPatterns3.add("/course/*");
		
		urlPatterns3.add("/studentList");
		urlPatterns3.add("/student/*");
		
		urlPatterns3.add("/subjectList");
		urlPatterns3.add("/subject/*");

		urlPatterns3.add("/department/*");
		
		urlPatterns3.add("/signup");
		
		mapConfig.put(ROLE_ADMIN, urlPatterns3);
	}

	public static Set<String> getAllAppRoles() {
		return mapConfig.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}
}
