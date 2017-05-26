package com.mkren.building.dao.mysql.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.mkren.building.entity.MagazineEntity;
import com.mkren.building.entity.RecordsArchiveEntity;
import com.mkren.building.entity.SmetaEntity;
import com.mkren.building.entity.UserEntity;

public final class ResultSetConverter {

	private ResultSetConverter() {
		throw new AssertionError("Class contains static methods only. You should not instantiate it!");
	}

	public static UserEntity createUserEntity(ResultSet set) throws SQLException {
		
		Integer userId = set.getInt("id");
		String login = set.getString("login");
		String password = set.getString("password");
		String surnameInitials = set.getString("surname_initials");;
		String role = set.getString("role");
		String delStatus = set.getString("del_status");
		
		UserEntity entity = new UserEntity();

		entity.setId(userId);
		entity.setLogin(login);
		entity.setPassword(password);
		entity.setSurnameInitials(surnameInitials);
		entity.setRole(role);
		entity.setDelStatus(delStatus);

		return entity;
	}
	
	public static SmetaEntity createSmetaEntity(ResultSet set) throws SQLException {
		
		Integer userId = set.getInt("id");
		Integer pp = set.getInt("pp");
		String obosnovanie = set.getString("obosnovanie");
		String naimenovanie = set.getString("naimenovanie");
		String edIzm = set.getString("ed_izm");
		Double kolVo = set.getDouble("kol_vo");
		
		SmetaEntity entity = new SmetaEntity();

		entity.setId(userId);
		entity.setPp(pp);
		entity.setObosnovanie(obosnovanie);
		entity.setNaimenovanie(naimenovanie);
		entity.setEdIzm(edIzm);
		entity.setKolVo(kolVo);

		return entity;
	}
	
	public static MagazineEntity createMagazineEntity(ResultSet set) throws SQLException {
		
		Integer magazineId = set.getInt("id");
		Integer idSmeta = set.getInt("id_smeta");
		String location = set.getString("location");
		Date date = set.getDate("date_");
		String smena = set.getString("smena");
		String weather = set.getString("weather");
		String conditions = set.getString("conditions");
		Double volume = set.getDouble("volume");
		String controle = set.getString("controle");
		Integer idUser = set.getInt("id_user");
				
		MagazineEntity entity = new MagazineEntity();
		
		entity.setId(magazineId);
		entity.setIdSmeta(idSmeta);
		entity.setLocation(location);
		entity.setDate(date);
		entity.setSmena(smena);
		entity.setWeather(weather);
		entity.setConditions(conditions);
		entity.setVolume(volume);
		entity.setControle(controle);
		entity.setIdUser(idUser);
		
		return entity;
	}
	
	public static RecordsArchiveEntity createRecordsArchiveEntity(ResultSet set) throws SQLException {
		
		Integer recordsId = set.getInt("id");
		Integer idMag = set.getInt("id_mag");
		Date date = set.getDate("date_");
		String nameColumn = set.getString("name_column");
		String oldRecord = set.getString("old_record");
		Integer idUser = set.getInt("id_user");
				
		RecordsArchiveEntity entity = new RecordsArchiveEntity();
		
		entity.setId(recordsId);
		entity.setIdMag(idMag);
		entity.setDate(date);
		entity.setNameColumn(nameColumn);
		entity.setOldRecord(oldRecord);
		entity.setIdUser(idUser);
		
		return entity;
	}
}
