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
import com.mkren.building.service.RedactorService;

@WebServlet("/redactor.html")
public class RedactorController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// перечень пунктов сметы для формирования списка
		NewRecordService newRecord = FACTORY.getNewRecordService();
		List<SmetaBean> smetaBeans = newRecord.getAllSmeta();
		request.setAttribute(SMETA, smetaBeans);
				
		Integer magazineId = HttpUtils.getIntParam(request, MAGAZINE_ID);
		//сохраним magazineId в сессию для использования при сохранении записи
		session.setAttribute(MAGAZINE_ID, magazineId);
		
		// получаем выбранную запись для редактирования
		RedactorService service = FACTORY.getRedactorService();
		NewRecordBean oldRecord = service.loadRecord(magazineId);
		
		// сохраняем в сессию первоначальный вариант записи для редактирования
		session.setAttribute(OLD_RECORD, oldRecord);
		
		// перечень юзеров для формирования списка
		List<UserBean> users = service.loadAllUsers();
		request.setAttribute(USERS, users);
		
		request.setAttribute(TITLE, NEW_REDACTOR_TITLE);
		HttpUtils.forvardToMainPageJsp(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// загружаем старый и новый вариант записей
		HttpSession session = request.getSession();
		NewRecordBean oldRecord =(NewRecordBean) session.getAttribute(OLD_RECORD);
		NewRecordBean newRecord = loadData(request);
		
		// получим автора обновлений и номер записи в журнале
		UserBean author = (UserBean) session.getAttribute(USER);
		Integer magazineId = (Integer) session.getAttribute(MAGAZINE_ID);
		
		// обновляем запись
		RedactorService service = FACTORY.getRedactorService();
		service.updateRecord(newRecord, oldRecord, author, magazineId);
		
		ServletContext context = getServletContext();
		String rootContext = context.getContextPath();
		response.sendRedirect(rootContext + "/magazine.html");
	}
	
	private NewRecordBean loadData(HttpServletRequest request){
		
		NewRecordBean recordBean = new NewRecordBean();
		
		String smena = HttpUtils.getParameter(request, ADD_SMENA, "^[123]{1}$");
		Date date = HttpUtils.getDate(request, ADD_DATE);
		Integer ppSmeta = HttpUtils.getIntParam(request, PP_SMETA);
		String location = HttpUtils.getParameter(request, ADD_LOCATION, "^.*$");
		String weather = HttpUtils.getParameter(request, ADD_WEATHER, "^.*$");
		String conditions = HttpUtils.getParameter(request, ADD_CONDITIONS, "^.*$");
		Double kolVo = HttpUtils.getDoubleParam(request, ADD_KOL_VO);
		String controle = HttpUtils.getParameter(request, ADD_CONTROLE, "^.*$");
		Integer userId = HttpUtils.getIntParam(request, ADD_USER_ID);
		
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
}