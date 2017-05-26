package com.mkren.building.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mkren.building.bean.NewRecordBean;
import com.mkren.building.bean.SmetaBean;
import com.mkren.building.bean.UserBean;
import com.mkren.building.controller.util.HttpUtils;
import com.mkren.building.service.NewRecordService;

@WebServlet("/add-record.html")

public class NewRecordController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NewRecordBean newRecordBean = loadData(request);
		boolean beanIsValid = valdationNewRecordBean(newRecordBean);
		
		if(beanIsValid){
			NewRecordService newRecord = FACTORY.getNewRecordService();
			newRecord.storeNewRecord(newRecordBean);
		}
		
		ServletContext context = getServletContext();
		String rootContext = context.getContextPath();
		response.sendRedirect(rootContext + "/add-record.html");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NewRecordService newRecord = FACTORY.getNewRecordService();
		
		List<SmetaBean> smetaBeans = newRecord.getAllSmeta();
		request.setAttribute(SMETA, smetaBeans);
		
		request.setAttribute(TITLE, NEW_RECORD_TITLE);
		HttpUtils.forvardToMainPageJsp(request, response);
	}
	
	private NewRecordBean loadData(HttpServletRequest request){
		
		NewRecordBean recordBean = new NewRecordBean();
		
		String smena = HttpUtils.getParameter(request, ADD_SMENA, "^[123]{1}$");
		Date date = new Date(System.currentTimeMillis());
		Integer ppSmeta = HttpUtils.getIntParam(request, PP_SMETA);
		String location = HttpUtils.getParameter(request, ADD_LOCATION, "^.*$");
		String weather = HttpUtils.getParameter(request, ADD_WEATHER, "^.*$");
		String conditions = HttpUtils.getParameter(request, ADD_CONDITIONS, "^.*$");
		Double kolVo = HttpUtils.getDoubleParam(request, ADD_KOL_VO);
		String controle = HttpUtils.getParameter(request, ADD_CONTROLE, "^.*$");
			HttpSession session = request.getSession();
			UserBean userBean =(UserBean) session.getAttribute(USER);
		Integer userId = userBean.getId();
		
		recordBean.setSmena(smena);
		recordBean.setDate(date);
		recordBean.setPpSmeta(ppSmeta);
		recordBean.setLocation(location);
		recordBean.setWeather(weather);
		recordBean.setConditions(conditions);
		recordBean.setKolVo(kolVo);
		recordBean.setControle(controle);
		recordBean.setUserId(userId);
		
		return recordBean;
	}
	
	private boolean valdationNewRecordBean(NewRecordBean bean){
		if (bean.getSmena() == null) {
			return false;
		}

		if (bean.getDate() == null) {
			return false;
		}
		
		if (bean.getUserId()== null) {
			return false;
		}
		
		return true;
	}
}
