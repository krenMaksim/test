package com.mkren.building.dao;

import java.util.List;

import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.entity.MagazineEntity;

public interface MagazineDAO {
	public List<MagazineEntity> loadAllMagazine() throws DaoException;

	public MagazineEntity loadMagazineById(Integer magazineId) throws DaoException;
		
	public MagazineEntity storeMagazine(MagazineEntity magazine) throws DaoException;

//	public void storeMagazine(List<MagazineEntity> magazine) throws DaoException;

	public void updateMagazine(MagazineEntity magazine) throws DaoException;
	
//	public void removeMagazine(Integer idMagazine);
}
