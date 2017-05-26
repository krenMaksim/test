package com.mkren.building.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mkren.building.bean.UserBean;
import com.mkren.building.controller.util.HttpUtils;
import com.mkren.building.service.AvtorizationService;

@WebServlet("/index.html")
public class AvtorizationController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		session.removeAttribute(USER);
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(AVTORIZATION_PAGE);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = HttpUtils.getParameter(request, LOGIN, "^.*$");
		String password = HttpUtils.getParameter(request, PASSWORD, "^.*$");
		
		AvtorizationService avtorization = FACTORY.getAvtorizationService();
		UserBean user = avtorization.loadUserBean(login, password);
		
		ServletContext context = getServletContext();
		String rootContext = context.getContextPath();
		
		if(user != null){
			HttpSession session = request.getSession();
			session.setAttribute(USER, user);
			response.sendRedirect(rootContext +"/magazine.html");
		}else{
			request.setAttribute(AVTORIZATION_ERROR, ERROR_MASSAGE);
			RequestDispatcher dispatcher = context.getRequestDispatcher(AVTORIZATION_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
