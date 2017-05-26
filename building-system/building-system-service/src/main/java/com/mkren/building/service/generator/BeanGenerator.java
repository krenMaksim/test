package com.mkren.building.service.generator;

import java.util.List;

import com.mkren.building.bean.ArchiveBean;
import com.mkren.building.bean.MagazineBean;
import com.mkren.building.bean.NewRecordBean;
import com.mkren.building.bean.SmetaBean;
import com.mkren.building.bean.UserBean;
import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.factory.DAOFactory;
import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.entity.RecordsArchiveEntity;
import com.mkren.building.entity.SmetaEntity;
import com.mkren.building.entity.UserEntity;
import com.mkren.building.service.dozer.Dozer;
import com.mkren.building.service.dozer.Mapping;

public final class BeanGenerator {
	private static final DAOFactory FACTORY = DAOFactory.getFactory();
	
	private BeanGenerator() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}
	
	public static UserBean creatUserBean(UserEntity entity){
		return Dozer.uneversalConvert(entity, UserBean.class, Mapping.DEFAULT);
	}
	
	public static SmetaBean createSmetaBean(SmetaEntity entity){
		return Dozer.uneversalConvert(entity, SmetaBean.class, Mapping.CUSTOM);
	}
	
	public static MagazineBean createMagazineBean(MagazineEntity entity){
		return Dozer.uneversalConvert(entity, MagazineBean.class, Mapping.CUSTOM);
	}
	
	public static MagazineEntity createMagazineEntity(NewRecordBean bean){
		return Dozer.uneversalConvert(bean, MagazineEntity.class, Mapping.CUSTOM);
	}
	
	public static NewRecordBean createNewRecordBean(MagazineEntity entity){
		return Dozer.uneversalConvert(entity, NewRecordBean.class, Mapping.CUSTOM);
	}
	
	public static ArchiveBean createArchiveBean(Integer idMagazine){
		SmetaDAO smetaDAO = FACTORY.getSmetaDAO(); 
		UserDAO userDAO = FACTORY.getUserDAO(); 
		RecordsArchiveDAO archiveDAO = FACTORY.getRecordsArchiveDAO();
						
		ArchiveBean archiveBean = new ArchiveBean();
		
		StringBuffer date = new StringBuffer();
		StringBuffer smena = new StringBuffer();
		StringBuffer location = new StringBuffer();
		StringBuffer obosnovanie = new StringBuffer();
		StringBuffer weather = new StringBuffer();
		StringBuffer conditions = new StringBuffer();
		StringBuffer kolVo = new StringBuffer();
		StringBuffer controle = new StringBuffer();
		StringBuffer surnameInitials = new StringBuffer();
		
		List<RecordsArchiveEntity> archiveEntities = archiveDAO.loadRecordsArchiveByMagazineId(idMagazine);
		
		for(RecordsArchiveEntity entity: archiveEntities){
			String redactor = getRedactorOmen(entity);
			String oldRecord = entity.getOldRecord()+redactor;
			
			switch (entity.getNameColumn()) {
				case "date_":
					date.append(oldRecord);
					break;
				case "smena":
					smena.append(oldRecord);
					break;
				case "location":
					location.append(oldRecord);
					break;
				case "id_smeta":
					Integer idSmeta = Integer.valueOf(entity.getOldRecord());
					SmetaEntity smetaEntity = smetaDAO.loadSmetaById(idSmeta);
					obosnovanie.append(smetaEntity.getNaimenovanie()+redactor);		
					break;
				case "weather":
					weather.append(oldRecord);
					break;
				case "conditions":
					conditions.append(oldRecord);
					break;
				case "volume":
					kolVo.append(oldRecord);
					break;
				case "controle":
					controle.append(oldRecord);
					break;
				case "id_user":
					Integer idUser = Integer.valueOf(entity.getOldRecord());
					UserEntity userEntity = userDAO.loadUserById(idUser);
					surnameInitials.append(userEntity.getSurnameInitials()+" ");
					surnameInitials.append(userEntity.getRole());
					surnameInitials.append(oldRecord);			
					break;
			}
		}
		
		archiveBean.setDate(date.toString());
		archiveBean.setSmena(smena.toString());
		archiveBean.setLocation(location.toString());
		archiveBean.setObosnovanie(obosnovanie.toString());
		archiveBean.setWeather(weather.toString());
		archiveBean.setConditions(conditions.toString());
		archiveBean.setKolVo(kolVo.toString());
		archiveBean.setControle(controle.toString());
		archiveBean.setSurnameInitials(surnameInitials.toString());
		
		return archiveBean;
	}
	
	private static String getRedactorOmen(RecordsArchiveEntity entity){
		UserEntity userEntity = FACTORY.getUserDAO().loadUserById(entity.getIdUser());
		
		String surname = userEntity.getSurnameInitials();
		String role = userEntity.getRole();
		String date = entity.getDate().toString();
		
		return " [ "+date+": " + surname + " " + role + " ]; ";
	}
}
