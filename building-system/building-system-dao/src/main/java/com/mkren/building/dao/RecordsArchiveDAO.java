package com.mkren.building.dao;

import java.util.List;

import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.entity.RecordsArchiveEntity;

public interface RecordsArchiveDAO {
//	public List<RecordsArchiveEntity> loadAllRecordsArchive() throws DaoException;

	public RecordsArchiveEntity loadRecordsArchiveById(Integer recordId) throws DaoException;

	public RecordsArchiveEntity storeRecordsArchive(RecordsArchiveEntity record) throws DaoException;

//	public void storeRecordsArchive(List<RecordsArchiveEntity> record) throws DaoException;

//	public void updateRecordsArchive(RecordsArchiveEntity record) throws DaoException;
	
//	public void removeRecordsArchive(Integer idRecord);
	
	public List<RecordsArchiveEntity> loadRecordsArchiveByMagazineId(Integer magazineId) throws DaoException;
}
