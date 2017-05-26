package com.mkren.building.dao.factory;

import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.UserDAO;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();

	public abstract MagazineDAO getMagazineDAO();
	
	public abstract RecordsArchiveDAO getRecordsArchiveDAO();
	
	public abstract SmetaDAO getSmetaDAO();
	
	public static DAOFactory getFactory() {
		return new MySqlDAOFactory();
	}
}
