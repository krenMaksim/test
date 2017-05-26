package com.mkren.building.controller;

import javax.servlet.http.HttpServlet;

import com.mkren.building.service.factory.ServiceFactory;

public class BaseController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected static final ServiceFactory FACTORY = ServiceFactory.getFactory();
	// common
	public static final String TITLE = "title";
	// avtorization controller
	public static final String USER = "user";
	public static final String EXIT = "exit";
	public static final String AVTORIZATION_PAGE = "/WEB-INF/pages/avtorization.jsp";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String AVTORIZATION_ERROR = "avtorizationError";
	public static final String ERROR_MASSAGE = "application.error";
	// magazine controller
	public static final String MAGAZINE_TITLE = "magaz.title";
	public static final String MAIN = "/WEB-INF/pages/main.jsp";
	public static final String DATE_WITH = "date_with";
	public static final String DATE_ON = "date_on";
	public static final String SURNAME_INITIALS = "surname_initials";
	public static final String RECORDS = "records";
	public static final String SURNAME = "surname";
	public static final String SHOW_PAGE = "showPage";
	public static final String REQUEST_URI = "requestURI";
	// newRecord controller
	public static final String NEW_RECORD_TITLE = "record.title";
	public static final String SMETA = "smeta";
	public static final String PP_SMETA = "ppSmeta";
	public static final String ADD_LOCATION = "addLocation";
	public static final String ADD_SMENA = "addSmena";
	public static final String ADD_WEATHER = "addWeather";
	public static final String ADD_CONDITIONS = "addConditions";
	public static final String ADD_KOL_VO = "addKolVo";
	public static final String ADD_CONTROLE = "addControle";
	// redactor controller
	public static final String NEW_REDACTOR_TITLE = "redactor.title";
	public static final String MAGAZINE_ID = "magazineId";
	public static final String OLD_RECORD = "oldRecord";
	public static final String USERS = "users";
	public static final String ADD_USER_ID = "addUserId";
	public static final String ADD_DATE = "addDate";
	// smeta controller
	public static final String SMETA_TITLE = "smeta.title";
	public static final String SMETA_PAGE = "/WEB-INF/pages/smeta.jsp";
}
