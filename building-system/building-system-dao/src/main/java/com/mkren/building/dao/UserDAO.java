package com.mkren.building.dao;

import java.util.List;

import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.entity.UserEntity;

public interface UserDAO {
	public List<UserEntity> loadAllUsers() throws DaoException;

	public UserEntity loadUserById(Integer userId) throws DaoException;
	
	public UserEntity loadUserBySurname(String surname) throws DaoException;
	
	public UserEntity loadUserByLogin(String login) throws DaoException;

//	public UserEntity storeUser(UserEntity user) throws DaoException;

//	public void storeUsers(List<UserEntity> users) throws DaoException;

//	public void updateUser(UserEntity user) throws DaoException;
	
//	public void removeUser(Integer idUser);
}
