package com.mkren.building.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.mkren.building.bean.UserBean;
import com.mkren.building.controller.BaseController;
import com.mkren.building.controller.util.HttpUtils;
import com.mkren.building.service.util.StringUtils;

@WebFilter("/*")
public class AppFilter implements Filter {

    public void destroy() {
    	//NOOP
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpUtils.setTypeEncoding(request, response);
		
		String locale = request.getParameter("locale");
        if (StringUtils.isNotEmpty(locale)) {
            HttpSession session = request.getSession();
            Config.set(session, Config.FMT_LOCALE, locale);
        }
		
		String servletPath = request.getServletPath();
		switch (servletPath) {
			case "/magazine.html":
			case "/smeta.html":
			case "/add-record.html":
			case "/redactor.html":
				HttpSession session = request.getSession();
				UserBean user = (UserBean)session.getAttribute(BaseController.USER);
				if(user == null){
					String rootContext = request.getContextPath();
					response.sendRedirect(rootContext +"/index.html");
					return;
				}
			break;
		}
		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//NOOP
	}
}