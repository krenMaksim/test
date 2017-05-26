package com.mkren.building.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mkren.building.bean.NewRecordBean;
import com.mkren.building.bean.SmetaBean;
import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.entity.SmetaEntity;
import com.mkren.building.service.NewRecordService;
import com.mkren.building.service.generator.BeanGenerator;

public final class NewRecordServiceImpl implements NewRecordService {
	private SmetaDAO smetaDao;
	private MagazineDAO magazineDAO;
	
	public NewRecordServiceImpl(){
		DAOFactory factory = DAOFactory.getFactory();
		smetaDao = factory.getSmetaDAO();
		magazineDAO = factory.getMagazineDAO();
	}
	
	@Override
	public List<SmetaBean> getAllSmeta(){
		List<SmetaEntity> listEntity = smetaDao.loadAllSmeta();
		
		List<SmetaBean> listBeans = new ArrayList<SmetaBean>();
		
		for (SmetaEntity entity : listEntity) {
			SmetaBean bean = BeanGenerator.createSmetaBean(entity);
			listBeans.add(bean);
		}
		
		return listBeans;
	}
	
	@Override
	public void storeNewRecord(NewRecordBean recordBean){
		MagazineEntity magazine = BeanGenerator.createMagazineEntity(recordBean);
		magazineDAO.storeMagazine(magazine);
	}
	
}
