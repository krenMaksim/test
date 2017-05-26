package com.mkren.building.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.bean.NewRecordBean;
import com.mkren.building.bean.UserBean;
import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.entity.RecordsArchiveEntity;
import com.mkren.building.entity.UserEntity;
import com.mkren.building.service.RedactorService;
import com.mkren.building.service.generator.BeanGenerator;

public final class RedactorServiceImpl implements RedactorService {
	private static final String SMENA = "smena";
	private static final String DATE = "date_";
	private static final String SMETA = "id_smeta";
	private static final String LOCATION = "location";
	private static final String WEATHER = "weather";
	private static final String CONDITIONS = "conditions";
	private static final String KOL_VO = "volume";
	private static final String CONTROLE = "controle";
	private static final String ID_USER = "id_user";
	
	private MagazineDAO magazineDAO;
	private SmetaDAO smetaDAO;
	private UserDAO userDAO;
	private RecordsArchiveDAO recordsArchiveDAO;
	
	public RedactorServiceImpl(){
		DAOFactory factory = DAOFactory.getFactory();
		magazineDAO = factory.getMagazineDAO();
		smetaDAO = factory.getSmetaDAO();
		userDAO = factory.getUserDAO();
		recordsArchiveDAO = factory.getRecordsArchiveDAO();
	}

	@Override
	public NewRecordBean loadRecord(Integer id) {
		MagazineEntity entity = magazineDAO.loadMagazineById(id);
		NewRecordBean bean = BeanGenerator.createNewRecordBean(entity);
		
		return bean;
	}

	@Override
	public List<UserBean> loadAllUsers() {
		
		List<UserEntity> entities = userDAO.loadAllUsers();
		List<UserBean> beans = new ArrayList<UserBean>();
		
		for(UserEntity entity: entities){
			UserBean bean = BeanGenerator.creatUserBean(entity);
			beans.add(bean);
		}
		
		return beans;
	}

	@Override
	public void updateRecord(NewRecordBean newRecord, NewRecordBean oldRecord, UserBean author, Integer magazineId) {
		//обновление всей записи
		boolean records = isEquals(newRecord, oldRecord);
		if(records){
			return;
		}else{
			MagazineEntity entity = BeanGenerator.createMagazineEntity(newRecord);
			entity.setId(magazineId);
			magazineDAO.updateMagazine(entity);
		}
		
		// добавляем данные в таблицу исправлений
		RecordsArchiveEntity entity = null;
		
		boolean smena = isEquals(newRecord.getSmena(),oldRecord.getSmena());
		if(!smena){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(SMENA);
			entity.setOldRecord(oldRecord.getSmena());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean date = isEquals(newRecord.getDate(),oldRecord.getDate());
		if(!date){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(DATE);
			entity.setOldRecord(oldRecord.getDate().toString());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean ppSmeta = isEquals(newRecord.getPpSmeta(),oldRecord.getPpSmeta());
		if(!ppSmeta){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(SMETA);
				Integer idSmeta = smetaDAO.loadIdByPp(oldRecord.getPpSmeta());
				if(idSmeta !=null){
					entity.setOldRecord(idSmeta.toString());
					recordsArchiveDAO.storeRecordsArchive(entity);
				}
		}
		
		boolean location = isEquals(newRecord.getLocation(),oldRecord.getLocation());
		if(!location){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(LOCATION);
			entity.setOldRecord(oldRecord.getLocation());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean weather = isEquals(newRecord.getWeather(),oldRecord.getWeather());
		if(!weather){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(WEATHER);
			entity.setOldRecord(oldRecord.getWeather());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean conditions = isEquals(newRecord.getConditions(),oldRecord.getConditions());
		if(!conditions){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(CONDITIONS);
			entity.setOldRecord(oldRecord.getConditions());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean kolVo = isEquals(newRecord.getKolVo(),oldRecord.getKolVo());
		if(!kolVo){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(KOL_VO);
			entity.setOldRecord(oldRecord.getKolVo().toString());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean controle = isEquals(newRecord.getControle(),oldRecord.getControle());
		if(!controle){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(CONTROLE);
			entity.setOldRecord(oldRecord.getControle());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
		
		boolean userId = isEquals(newRecord.getUserId(),oldRecord.getUserId());
		if(!userId){
			entity = preparedEntity(oldRecord, author, magazineId);
			entity.setNameColumn(ID_USER);
			entity.setOldRecord(oldRecord.getUserId().toString());
			recordsArchiveDAO.storeRecordsArchive(entity);
		}
	}
	
	private RecordsArchiveEntity preparedEntity(NewRecordBean oldRecord, UserBean author, Integer magazineId){
		RecordsArchiveEntity entity = new RecordsArchiveEntity();
		entity.setIdMag(magazineId);
		entity.setDate(new Date(System.currentTimeMillis()));
		entity.setIdUser(author.getId());
		
		return entity;
	}
	
	private <T> boolean isEquals(T objectOne, T objectTwo){
		if(objectOne == null && objectTwo == null){
			return true;
		}
		
		if(objectOne == null || objectTwo == null){
			return false;
		}
		
		return objectOne.equals(objectTwo);
	}
}