package com.mkren.building.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.bean.MagazineBean;
import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.service.MagazineService;
import com.mkren.building.service.generator.BeanGenerator;
import com.mkren.building.service.util.DateUtils;
import com.mkren.building.service.util.MagazineUtils;

public final class MagazineServiceImpl implements MagazineService {
	private MagazineDAO magazineDAO;
	
	public MagazineServiceImpl(){
		magazineDAO = DAOFactory.getFactory().getMagazineDAO();
	}
	
	@Override
	public List<MagazineBean> getRecords(Date dateWith, Date dateOn, List<String> surnames) {
		List<MagazineBean> magazineBeans = getAllRecords();
		List<MagazineBean> preparedMagazineBeans = new ArrayList<MagazineBean>(magazineBeans);
		
		for (MagazineBean bean : magazineBeans) {
			Date date = bean.getDate();
			// удаляем записи с датами вне выбранного промежутка
			if(!DateUtils.compareData(date, dateWith, dateOn)){
				preparedMagazineBeans.remove(bean);
			}
			
			if(!MagazineUtils.surnameIsConsist(bean.getSurnameInitials(), surnames)){
				preparedMagazineBeans.remove(bean);
			}
		}
		
		// если вдруг лист совсем пустой заполняем его пустым бином возможно это уберем
		if(preparedMagazineBeans.isEmpty()){
			preparedMagazineBeans.add(new MagazineBean());
		}
		
		return preparedMagazineBeans;
	}
	
	@Override
	public List<String> surnameInitials(){
		List<MagazineBean> listBeans = getAllRecords();
		List<String> surnameInitials = new ArrayList<String>();
		
		for (MagazineBean bean : listBeans) {
			String surname = bean.getSurnameInitials();
			if(!surnameInitials.contains(surname)){
				surnameInitials.add(surname);
			}
		}
		
		return surnameInitials;
	}
	
	private List<MagazineBean> getAllRecords(){
		List<MagazineEntity> listEntity = magazineDAO.loadAllMagazine();
		List<MagazineBean> listBeans = new ArrayList<MagazineBean>();
		
		for (MagazineEntity entity : listEntity) {
			MagazineBean bean = BeanGenerator.createMagazineBean(entity);
			listBeans.add(bean);
		}
		
		return listBeans;
	}
}