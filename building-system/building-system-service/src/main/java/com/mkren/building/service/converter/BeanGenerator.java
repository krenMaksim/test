package com.mkren.building.service.converter;

import java.sql.Date;
import java.util.List;

import com.mkren.building.bean.ArchiveBean;
import com.mkren.building.bean.MagazineBean;
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

public final class BeanGenerator {
	private static final DAOFactory FACTORY = DAOFactory.getFactory();
	private static final SmetaDAO SMETA_DAO = FACTORY.getSmetaDAO(); 
	private static final UserDAO USER_DAO = FACTORY.getUserDAO(); 
	private static final RecordsArchiveDAO ARCHIVE_DAO = FACTORY.getRecordsArchiveDAO(); 
	
	private BeanGenerator() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}
	
	public static UserBean creatUserBean(UserEntity entity){
		UserBean bean = new UserBean();
		
		Integer id = entity.getId();   
		String login = entity.getLogin();
		String password = entity.getPassword();
		String surnameInitials = entity.getSurnameInitials();
		String role = entity.getRole();
		
		bean.setId(id);
		bean.setLogin(login);;
		bean.setPassword(password);;
		bean.setSurnameInitials(surnameInitials);
		bean.setRole(role);
		
		return bean;
	}
	
	public static SmetaBean createSmetaBean(SmetaEntity entity){
		SmetaBean bean = new SmetaBean();
		
		Integer pp = entity.getPp();
		String obosnovanie = entity.getObosnovanie();
		String naimenovanie = entity.getNaimenovanie();
		String edIzm = entity.getEdIzm();
		Double kolVo = entity.getKolVo();
		Double rest = SMETA_DAO.getRestFromVolumesById(entity.getId());
			
		if(rest == null){
			rest = entity.getKolVo();
		}
		
		bean.setPp(pp);
		bean.setObosnovanie(obosnovanie);
		bean.setNaimenovanie(naimenovanie);
		bean.setEdIzm(edIzm);
		bean.setKolVo(kolVo);
		bean.setRest(rest);
		
		return bean;
	}
	
	public static MagazineBean createMagazineBean(MagazineEntity entity){
		MagazineBean bean = new MagazineBean();
		
		Integer id = entity.getId();
		Date date = entity.getDate();
		String smena = entity.getSmena();
		String location = entity.getLocation();
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
		String weather = entity.getWeather();
		String conditions = entity.getConditions();
		Double kolVo = entity.getVolume();
		String controle = entity.getControle();
		String surnameInitials = getSurnameInitials(entity.getIdUser());
		String role = getRole(entity.getIdUser());
		
		//вот сюда мы вставим метод добавления архивной записи
		ArchiveBean archive = createArchiveBean(entity.getId());
		
		bean.setId(id);
		bean.setDate(date);
		bean.setSmena(smena);
		bean.setObosnovanie(obosnovanie);;
		bean.setLocation(location);
		bean.setWeather(weather);
		bean.setConditions(conditions);
		bean.setEdIzm(edIzm);
		bean.setKolVo(kolVo);
		bean.setControle(controle);
		bean.setSurnameInitials(surnameInitials);
		bean.setRole(role);
		bean.setArchive(archive);
		
		return bean;
	}
	
	private static ArchiveBean createArchiveBean(Integer idMagazine){
						
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
		
		List<RecordsArchiveEntity> archiveEntities = ARCHIVE_DAO.loadRecordsArchiveByMagazineId(idMagazine);
		
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
					SmetaEntity smetaEntity = SMETA_DAO.loadSmetaById(idSmeta);
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
					UserEntity userEntity = USER_DAO.loadUserById(idUser);
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
		UserEntity userEntity = USER_DAO.loadUserById(entity.getIdUser());
		
		String surname = userEntity.getSurnameInitials();
		String role = userEntity.getRole();
		String date = entity.getDate().toString();
		
		return " [ "+date+": " + surname + " " + role + " ]; ";
	}
	
	private static String getObosnovanie(Integer smetaId){
		SmetaEntity smetaEntity = SMETA_DAO.loadSmetaById(smetaId);
		
		return smetaEntity.getNaimenovanie();
	}
	
	private static String getEdIzm(Integer smetaId){
		SmetaEntity smetaEntity = SMETA_DAO.loadSmetaById(smetaId);
		
		return smetaEntity.getEdIzm();
	}
	
	private static String getRole(Integer userId){
		UserEntity userEntity = USER_DAO.loadUserById(userId);
		return userEntity.getRole();
	}
	
	private static String getSurnameInitials(Integer userId){
		UserEntity userEntity = USER_DAO.loadUserById(userId);
		return userEntity.getSurnameInitials(); 
	}
}
