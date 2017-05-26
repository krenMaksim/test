package com.mkren.building.service;

import com.mkren.building.bean.UserBean;

public interface AvtorizationService {
	public UserBean loadUserBean(String login, String password);
}
