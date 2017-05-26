package com.mkren.building.controller.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkren.building.controller.BaseController;
import com.mkren.building.service.util.DateUtils;
import com.mkren.building.service.util.StringUtils;

public final class HttpUtils {

	private HttpUtils() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}

	public static void setTypeEncoding(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
	}

	public static String getParameter(HttpServletRequest request, String paramName, String regex) {
		String valueStr = request.getParameter(paramName);
		if (StringUtils.isValidation(valueStr, regex)) {
			return valueStr;
		} else {
			return null;
		}
	}

	public static List<String> getParameter(HttpServletRequest request, String paramName) {
		String[] array = request.getParameterValues(paramName);
		List<String> parametrs = new ArrayList<String>();

		if (array != null) {
			parametrs = Arrays.asList(array);
		}

		return parametrs;
	}

	public static Date getDate(HttpServletRequest request, String paramName) {
		String strDate = request.getParameter(paramName);
		java.util.Date date = DateUtils.parseDate(strDate);

		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}
	
	public static Integer getIntParam(HttpServletRequest request, String paramName) {
		String valueStr = request.getParameter(paramName);
		
		try {
			return new Integer(valueStr);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Double getDoubleParam(HttpServletRequest request, String paramName) {
		String valueStr = request.getParameter(paramName);
		
		try {
			return new Double(valueStr);
		} catch (Exception e) {
			return null;
		}
	}

	public static void forvardToMainPageJsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// вводим атрибут, который показывает какую страницу импортировать в
		// main.jsp
		request.setAttribute(BaseController.SHOW_PAGE, request.getServletPath());

		// атрибуд для постановки в тег form в main.jsp
		request.setAttribute(BaseController.REQUEST_URI, request.getRequestURI());

		ServletContext context = request.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(BaseController.MAIN);
		dispatcher.forward(request, response);
	}
}
