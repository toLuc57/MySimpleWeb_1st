package org.hm.SimpleWeb.module;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hm.SimpleWeb.utils.CourseDBUtils;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;

public class SearchModule {
	public static String getSQLWhere(HttpServletRequest request, String className) {
		String[] listColunm =  request.getParameterValues("example");
		Map<String,String> mapColumn;
		switch(className) {
		case CourseDBUtils.className:
			mapColumn = CourseDBUtils.getAllColumnNameAndTypeName();
			break;
		case DepartmentDBUtils.className:
			mapColumn = DepartmentDBUtils.getAllColumnNameAndTypeName();
			break;
		case StudentDBUtils.className:
			mapColumn = CourseDBUtils.getAllColumnNameAndTypeName();
			break;
		case SubjectDBUtils.className:
			mapColumn = DepartmentDBUtils.getAllColumnNameAndTypeName();
			break;
		case TeacherDBUtils.className:
			mapColumn = CourseDBUtils.getAllColumnNameAndTypeName();
			break;
		case LearningOutcomesDBUtils.className:
			mapColumn = CourseDBUtils.getAllColumnNameAndTypeName();
			break;
		default:
			return " ";
		}
		String queryWhere = " ";
		if(listColunm != null) {
			int numberOfLoop = 1;
			//System.out.println("  Lengh:" + listColunm.length);
			for(String i: listColunm) {
				System.out.println("------START-------");
				System.out.println("  " + i + request.getParameter(i));
				request.setAttribute("variable", request.getParameter(i));
				queryWhere = queryWhere.concat(i);
				String typeOfValue = mapColumn.get(i);
				if(typeOfValue == "VARCHAR" || typeOfValue == "CHAR") {
					queryWhere = queryWhere.concat(" LIKE N\'%");
					queryWhere = queryWhere.concat(request.getParameter(i));
					queryWhere = queryWhere.concat("%\'");
				}
				if(numberOfLoop < listColunm.length) {
					queryWhere = queryWhere.concat(" AND ");
				}
				++numberOfLoop;
				System.out.println("-------END--------");
			}
		}
		return queryWhere;
	}
}
