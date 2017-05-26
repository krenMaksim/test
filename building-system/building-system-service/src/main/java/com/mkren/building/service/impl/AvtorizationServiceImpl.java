package com.mkren.building.service.impl;

import com.mkren.building.bean.UserBean;
import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.entity.UserEntity;
import com.mkren.building.service.AvtorizationService;
import com.mkren.building.service.generator.BeanGenerator;

public class AvtorizationServiceImpl implements AvtorizationService {
	private UserDAO users;
	
	public AvtorizationServiceImpl(){
		users = DAOFactory.getFactory().getUserDAO();
	}
	
	@Override
	public UserBean loadUserBean(String login, String password){
		UserBean userBean = null;
		UserEntity userEntity = users.loadUserByLogin(login);
		
		if(userEntity != null){
			if(password.equals(userEntity.getPassword())){
				userBean = BeanGenerator.creatUserBean(userEntity);
			}
		}
		
		return userBean;
	}
}