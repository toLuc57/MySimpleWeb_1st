package org.hm.SimpleWeb.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
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
		urlPatterns1.add("/departmentList");
		urlPatterns1.add("/courseList");
		urlPatterns1.add("/subjectList");
		urlPatterns1.add("/teacherList");

		mapConfig.put(ROLE_STUDENT, urlPatterns1);

		// Configure role "TEACHER".
		List<String> urlPatterns2 = new ArrayList<String>();

		urlPatterns2.add("/userInfo");
		
		urlPatterns2.add("/teacherList");
		urlPatterns2.add("/teacher/*");
		
		urlPatterns2.add("/learningOutcomesList");
		urlPatterns2.add("/learningOutcomes/*");
		
		urlPatterns2.add("/courseList");
		urlPatterns2.add("/course/*");
		
		urlPatterns2.add("/studentList");
		urlPatterns2.add("/student/*");
		
		urlPatterns2.add("/subjectList");
		urlPatterns2.add("/subject/*");
		
		urlPatterns2.add("/departmentList");
		urlPatterns2.add("/department/*");
		
		mapConfig.put(ROLE_TEACHER, urlPatterns2);
	}

	public static Set<String> getAllAppRoles() {
		return mapConfig.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}
}
