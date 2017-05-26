package com.mkren.building.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkren.building.bean.MagazineBean;
import com.mkren.building.controller.util.HttpUtils;
import com.mkren.building.service.MagazineService;

@WebServlet("/magazine.html")
public class MagazineController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Date dateWith = HttpUtils.getDate(request, DATE_WITH);
		Date dateOn = HttpUtils.getDate(request, DATE_ON);
		List<String> surnames = HttpUtils.getParameter(request, SURNAME_INITIALS);
		
		makeBisnessLogic(dateWith, dateOn, surnames, request);
		
		HttpUtils.forvardToMainPageJsp(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		makeBisnessLogic(null, null, null, request);
		
		HttpUtils.forvardToMainPageJsp(request, response);
	}
	
	private void makeBisnessLogic(Date dateWith,Date dateOn,List<String> surnames, HttpServletRequest request){
		MagazineService magazine = FACTORY.getMagazineService();
		
		List<MagazineBean> magazineBeans = magazine.getRecords(dateWith, dateOn, surnames);
		request.setAttribute(RECORDS, magazineBeans);
		
		List<String> surnameInitials = magazine.surnameInitials();
		request.setAttribute(SURNAME, surnameInitials);
		
		request.setAttribute(TITLE, MAGAZINE_TITLE);
	}
}