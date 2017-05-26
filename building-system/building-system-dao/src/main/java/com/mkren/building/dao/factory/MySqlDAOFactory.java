package com.mkren.building.dao.factory;

import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.mysql.MagazineDaoImpl;
import com.mkren.building.dao.mysql.RecordsArchiveDaoImpl;
import com.mkren.building.dao.mysql.SmetaDaoImpl;
import com.mkren.building.dao.mysql.UserDaoImpl;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserDaoImpl();
	}

	@Override
	public MagazineDAO getMagazineDAO() {
		return new MagazineDaoImpl();
	}

	@Override
	public RecordsArchiveDAO getRecordsArchiveDAO() {
		return new RecordsArchiveDaoImpl();
	}

	@Override
	public SmetaDAO getSmetaDAO() {
		return new SmetaDaoImpl();
	}
}
