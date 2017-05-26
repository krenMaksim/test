package com.mkren.building.service.converter;

import com.mkren.building.bean.NewRecordBean;
import com.mkren.building.entity.MagazineEntity;

public class NewRecordConverter extends BaseCustomConverter {
	private static final Integer NO_ID_SMETA = 0;
	
	public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3) {
		if (source == null){ 
            return null;
		}
		
		Integer idSmeta = null;
		Integer ppSmeta = null;
		
        if (source instanceof MagazineEntity) {
        	MagazineEntity entity = (MagazineEntity)source;
        	NewRecordBean bean = defaultConvert(entity, NewRecordBean.class);
        	
        	idSmeta = entity.getIdSmeta();
    		if(idSmeta != NO_ID_SMETA){
    			ppSmeta = SMETA_DAO.loadSmetaById(idSmeta).getPp();
    		}
        	
    		bean.setPpSmeta(ppSmeta);
    		
            return bean;
        }else if(source instanceof NewRecordBean){
        	NewRecordBean bean = (NewRecordBean)source;
        	MagazineEntity entity = defaultConvert(bean, MagazineEntity.class);
        	
        	ppSmeta = bean.getPpSmeta();    		
    		idSmeta = SMETA_DAO.loadIdByPp(ppSmeta);
    		entity.setIdSmeta(idSmeta);
        	
        	return entity;
        }
        
        return null;
	}
}
