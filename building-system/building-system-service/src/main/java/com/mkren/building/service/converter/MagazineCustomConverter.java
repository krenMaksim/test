package com.mkren.building.service.converter;

import com.mkren.building.bean.ArchiveBean;
import com.mkren.building.bean.MagazineBean;
import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.entity.SmetaEntity;
import com.mkren.building.entity.UserEntity;
import com.mkren.building.service.generator.BeanGenerator;

public class MagazineCustomConverter extends BaseCustomConverter {

	public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3) {
		if (source == null){ 
            return null;
		}
        if (source instanceof MagazineEntity) {
        	MagazineEntity entity = (MagazineEntity)source;
        	MagazineBean bean = defaultConvert(entity, MagazineBean.class);
        	
        	String obosnovanie = null;
    		String edIzm = null;
    			// для отображения полупустых записей в журнале
    			Integer idSmeta = entity.getIdSmeta();
    			if(idSmeta >0){
    				obosnovanie = getObosnovanie(entity.getIdSmeta());
    				edIzm = getEdIzm(entity.getIdSmeta());
    			}else{
    				obosnovanie = "";
    				edIzm = "";
    			}
    		bean.setObosnovanie(obosnovanie);
    		bean.setEdIzm(edIzm);
    		
    		String surnameInitials = getSurnameInitials(entity.getIdUser());
    		String role = getRole(entity.getIdUser());
    		bean.setSurnameInitials(surnameInitials);
    		bean.setRole(role);
    		
    		ArchiveBean archive = BeanGenerator.createArchiveBean(entity.getId());
    		bean.setArchive(archive);
    		
            return bean;
        }
        return null;
	}
	
	private String getObosnovanie(Integer smetaId){
		SmetaEntity smetaEntity = SMETA_DAO.loadSmetaById(smetaId);
		
		return smetaEntity.getNaimenovanie();
	}
	
	private String getEdIzm(Integer smetaId){
		SmetaEntity smetaEntity = SMETA_DAO.loadSmetaById(smetaId);
		
		return smetaEntity.getEdIzm();
	}
	
	private String getRole(Integer userId){
		UserEntity userEntity = USER_DAO.loadUserById(userId);
		return userEntity.getRole();
	}
	
	private String getSurnameInitials(Integer userId){
		UserEntity userEntity = USER_DAO.loadUserById(userId);
		return userEntity.getSurnameInitials(); 
	}
}
