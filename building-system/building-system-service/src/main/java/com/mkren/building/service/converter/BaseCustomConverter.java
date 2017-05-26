package com.mkren.building.service.converter;

import org.dozer.CustomConverter;

import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.service.dozer.Dozer;
import com.mkren.building.service.dozer.Mapping;

public abstract class BaseCustomConverter implements CustomConverter{
	private static final DAOFactory FACTORY = DAOFactory.getFactory();
	protected static final SmetaDAO SMETA_DAO = FACTORY.getSmetaDAO(); 
	protected static final UserDAO USER_DAO = FACTORY.getUserDAO(); 
	protected static final RecordsArchiveDAO ARCHIVE_DAO = FACTORY.getRecordsArchiveDAO();
	
	protected <T,V> V defaultConvert(T sourse, Class<V> destClass) {
		return Dozer.uneversalConvert(sourse, destClass, Mapping.DEFAULT);
	}
	
	@Override
	abstract public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3);
}
