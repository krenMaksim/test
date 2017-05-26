package com.mkren.building.dao;

import java.util.List;

import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.entity.SmetaEntity;

public interface SmetaDAO {
	public List<SmetaEntity> loadAllSmeta() throws DaoException;

	public SmetaEntity loadSmetaById(Integer smetaId) throws DaoException;

	public Integer loadIdByPp(Integer smetaPP) throws DaoException;
	
	public Double getRestFromVolumesById(Integer smetaId) throws DaoException;
	
//	public SmetaEntity storeSmeta(SmetaEntity smeta) throws DaoException;

//	public void storeSmeta(List<SmetaEntity> smeta) throws DaoException;

//	public void updateSmeta(SmetaEntity smeta) throws DaoException;
	
//	public void removeSmeta(Integer idSmeta);
}
