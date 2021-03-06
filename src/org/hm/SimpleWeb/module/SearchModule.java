package org.hm.SimpleWeb.module;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hm.SimpleWeb.beans.UserAccount;
import org.hm.SimpleWeb.utils.CourseDBUtils;
import org.hm.SimpleWeb.utils.DepartmentDBUtils;
import org.hm.SimpleWeb.utils.LearningOutcomesDBUtils;
import org.hm.SimpleWeb.utils.MyUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;
import org.hm.SimpleWeb.utils.SubjectDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;

public class SearchModule {
	public static String getSQLWhere(HttpServletRequest request, String className) {
		String search = request.getParameter("search");
		UserAccount loginedUser = MyUtils.getLoginedUser(request.getSession());
		if(loginedUser == null) {
			request.setAttribute("nameURI", request.getRequestURI());
			if(search != null) {
				request.setAttribute("queryStringInUrl", "?search=" + search);
				request.setAttribute("search", search);
			}
			return " ";
		}else if (loginedUser.getIsStudent() || loginedUser.getIsTeacher()) {
			request.setAttribute("nameURI", request.getRequestURI());
			if(search != null) {
				request.setAttribute("queryStringInUrl", "?search=" + search);
				request.setAttribute("search", search);
			}
			return " ";
		}
		// Admin có 2 loại kiểu tìm kiếm
		// Nếu tìm kiếm loại 1 thì không cần làm tìm kiếm loại 2
		if(search != null) {
			request.setAttribute("nameURI", request.getRequestURI());
			request.setAttribute("queryStringInUrl", "?search=" + search);
			request.setAttribute("search", search);
			return " ";
		}
		// Tìm kiếm loại 2
		List<String> listColunm = null;
		Map<String,String> mapColumn;
		String queryStringInUrl = "?";
		switch(className) {
		case CourseDBUtils.className:
			mapColumn = CourseDBUtils.getAllColumnNameAndTypeName();
			listColunm = CourseDBUtils.getColumnName();
			break;
		case DepartmentDBUtils.className:
			mapColumn = DepartmentDBUtils.getAllColumnNameAndTypeName();
			listColunm =  DepartmentDBUtils.getColumnName();
			break;
		case StudentDBUtils.className:
			mapColumn = StudentDBUtils.getAllColumnNameAndTypeName();
			listColunm =  StudentDBUtils.getColumnName();
			break;
		case SubjectDBUtils.className:
			mapColumn = SubjectDBUtils.getAllColumnNameAndTypeName();
			listColunm = SubjectDBUtils.getColumnName();
			break;
		case TeacherDBUtils.className:
			mapColumn = TeacherDBUtils.getAllColumnNameAndTypeName();
			listColunm = TeacherDBUtils.getColumnName();
			break;
		case LearningOutcomesDBUtils.className:
			mapColumn = LearningOutcomesDBUtils.getAllColumnNameAndTypeName();
			listColunm =  LearningOutcomesDBUtils.getColumnName();
			break;
		default:
			return " ";
		}
		String queryWhere = " ";
		if(listColunm != null && !listColunm.isEmpty()) {
			queryWhere = " where ";
			int numberOfColumnChecked = 0;
			// Lấy mỗi cột trong table ra
			// Sau đó kiểm tra bên request xem người dùng có nhập không
			// Nếu không thì chuyển sang cột tiếp theo
			System.out.println("-----SearchModule-----");
			for(String i: listColunm) {
				System.out.println("------START" + i + "-------");
				//System.out.println("  " + i + request.getParameter(i));
				String typeOfValue = mapColumn.get(i);
				// Kiếm tra kiểu dữ liệu
				// Nếu kiểu dữ liệu là chuỗi
				// thì ta sẽ tìm kiếm chuỗi có từ khóa cần tìm ('%value%')				
				// Nếu không phải là chuỗi
				// thì ta sẽ tìm kiếm trong đoạn [fromValue;toValue]
				// Suy ra sẽ có 4 TH
				if(typeOfValue == "VARCHAR" || typeOfValue == "CHAR") {
					String value = request.getParameter(i);
					if(value == null || value.isEmpty()) {
						continue;
					}
					else {
						++numberOfColumnChecked;
						if(numberOfColumnChecked > 1) {
							queryWhere = queryWhere.concat(" AND ");
							queryStringInUrl = queryStringInUrl.concat("&");
						}
						
						queryWhere = queryWhere.concat(i);
						queryWhere = queryWhere.concat(" LIKE N\'%");
						queryWhere = queryWhere.concat(value);
						queryWhere = queryWhere.concat("%\'");
						request.setAttribute(i,value);
						
						queryStringInUrl = queryStringInUrl.concat(i);
						queryStringInUrl = queryStringInUrl.concat("=");
						queryStringInUrl = queryStringInUrl.concat(value);
					}
				}
				// Nếu kiểu dữ liệu là số
				else if(typeOfValue == "INT" || typeOfValue == "DOUBLE") {
					String startSTR = i.concat("_start");
					String endSTR = i.concat("_end");
					String fromValue = request.getParameter(startSTR);
					String toValue = request.getParameter(endSTR);
					if((fromValue == null && toValue == null) || 
							(fromValue.isEmpty() && toValue.isEmpty()) ) {
						continue;
					}
					else {
						++numberOfColumnChecked;
						if(numberOfColumnChecked > 1) {
							queryWhere = queryWhere.concat(" AND ");
							queryStringInUrl = queryStringInUrl.concat("&");
						}
						if((fromValue != null && toValue != null && 
								!fromValue.isBlank() && !toValue.isBlank())){
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" >= ");
							queryWhere = queryWhere.concat(fromValue);
							queryWhere = queryWhere.concat(" AND ");
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" <= ");
							queryWhere = queryWhere.concat(toValue);
							request.setAttribute(startSTR,fromValue);
							request.setAttribute(endSTR,toValue);
							
							queryStringInUrl = queryStringInUrl.concat(startSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(fromValue);
							queryStringInUrl = queryStringInUrl.concat("&");
							queryStringInUrl = queryStringInUrl.concat(endSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(toValue);
						}
						else if (fromValue != null && !fromValue.isBlank()) {
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" >= ");
							queryWhere = queryWhere.concat(fromValue);
							request.setAttribute(startSTR,fromValue);
							
							queryStringInUrl = queryStringInUrl.concat(startSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(fromValue);
						}
						else if (toValue != null && !toValue.isBlank()) {
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" <= ");
							queryWhere = queryWhere.concat(toValue);
							request.setAttribute(endSTR,toValue);
							
							queryStringInUrl = queryStringInUrl.concat(endSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(toValue);
						}
					}	
				}
				// Nếu kiểu dữ liệu là ngày
				// Giá trị ngày ở câu truy vấn database phải trong ''
				else if(typeOfValue == "DATE" || typeOfValue == "DATETIME") {
					String startSTR = i.concat("_start");
					String endSTR = i.concat("_end");
					String fromValue = request.getParameter(startSTR);
					String toValue = request.getParameter(endSTR);
					if((fromValue == null && toValue == null) || 
							(fromValue.isEmpty() && toValue.isEmpty()) ) {
						continue;
					}
					else {
						++numberOfColumnChecked;
						if(numberOfColumnChecked > 1) {
							queryWhere = queryWhere.concat(" AND ");
							queryStringInUrl = queryStringInUrl.concat("&");
						}
						if((fromValue != null && toValue != null && 
								!fromValue.isBlank() && !toValue.isBlank())){
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" >= ");
							queryWhere = queryWhere.concat("'" + fromValue + "'");
							queryWhere = queryWhere.concat(" AND ");
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" <= ");
							queryWhere = queryWhere.concat("'" + toValue + "'");
							request.setAttribute(startSTR,fromValue);
							request.setAttribute(endSTR,toValue);
							
							queryStringInUrl = queryStringInUrl.concat(startSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(fromValue);
							queryStringInUrl = queryStringInUrl.concat("&");
							queryStringInUrl = queryStringInUrl.concat(endSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(toValue);
						}
						else if (fromValue != null && !fromValue.isBlank()) {
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" >= ");
							queryWhere = queryWhere.concat("'" + fromValue + "'");
							request.setAttribute(startSTR,fromValue);
							
							queryStringInUrl = queryStringInUrl.concat(startSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(fromValue);
						}
						else if (toValue != null && !toValue.isBlank()) {
							queryWhere = queryWhere.concat(i);
							queryWhere = queryWhere.concat(" <= ");
							queryWhere = queryWhere.concat("'" + toValue + "'");
							request.setAttribute(endSTR,toValue);
							
							queryStringInUrl = queryStringInUrl.concat(endSTR);
							queryStringInUrl = queryStringInUrl.concat("=");
							queryStringInUrl = queryStringInUrl.concat(toValue);
						}
					}
						
				}
				System.out.println("-------END--------");
			}
		}
		request.setAttribute("nameURI", request.getRequestURI());
		request.setAttribute("queryStringInUrl", queryStringInUrl);
		System.out.println(queryStringInUrl);
		//System.out.println(request.getRequestURI());
		System.out.println(queryWhere);
		System.out.println("-----End SearchModule-----");
		if(queryWhere.equals(" where ")) {
			return " ";
		}
		return queryWhere;
	}
}
