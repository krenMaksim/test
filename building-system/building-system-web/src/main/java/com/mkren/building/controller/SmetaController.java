package com.mkren.building.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkren.building.bean.SmetaBean;
import com.mkren.building.service.NewRecordService;

@WebServlet("/smeta.html")
public class SmetaController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		String rootContext = context.getContextPath();
		response.sendRedirect(rootContext +"/smeta.html");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NewRecordService newRecord = FACTORY.getNewRecordService();
		
		List<SmetaBean> smetaBeans = newRecord.getAllSmeta();
		request.setAttribute(SMETA, smetaBeans);
		
		request.setAttribute(TITLE, SMETA_TITLE);
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(SMETA_PAGE);
		dispatcher.forward(request, response);
	}
}