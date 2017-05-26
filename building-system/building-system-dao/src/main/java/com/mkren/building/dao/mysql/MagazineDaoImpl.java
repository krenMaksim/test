package com.mkren.building.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.dao.MagazineDAO;
import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.dao.mysql.db.ConnectionPool;
import com.mkren.building.dao.mysql.db.ResultSetConverter;
import com.mkren.building.entity.MagazineEntity;

public class MagazineDaoImpl extends BaseDao implements MagazineDAO{
	
	@Override
	public List<MagazineEntity> loadAllMagazine() throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		List<MagazineEntity> result = new ArrayList<MagazineEntity>();

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT * from building_system.magazine;");
			set = statement.executeQuery();

			while (set.next()) {
				MagazineEntity entity = ResultSetConverter.createMagazineEntity(set);
				result.add(entity);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return result;
	}
	
	@Override
	public MagazineEntity loadMagazineById(Integer magazineId) throws DaoException {
		if (magazineId == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.magazine WHERE id=? ;");
			statement.setInt(1, magazineId);

			set = statement.executeQuery();

			if (set.next()) {
				MagazineEntity entity = ResultSetConverter.createMagazineEntity(set);
				return entity;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return null;
	}
	
	@Override
	public MagazineEntity storeMagazine(MagazineEntity magazine) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("INSERT INTO building_system.magazine (id_smeta, location, date_, smena, weather, conditions, volume, controle, id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			Integer idSmeta = magazine.getIdSmeta();
			// для отображения полупустых записей в журнале
			if(idSmeta != null){
				statement.setInt(1, idSmeta);
			}else{
				statement.setString(1, null);
			}
			statement.setString(2, magazine.getLocation());
			statement.setDate(3, magazine.getDate());
			statement.setString(4, magazine.getSmena());
			statement.setString(5, magazine.getWeather());
			statement.setString(6, magazine.getConditions());
			// для отображения полупустых записей в журнале
			Double volume = magazine.getVolume();
			if(volume != null){
				statement.setDouble(7, volume);
			}else {
				statement.setString(7, null);
			}
			statement.setString(8, magazine.getControle());
			statement.setInt(9, magazine.getIdUser());

			statement.executeUpdate();

			Integer storedId = loadStoredId(statement);
			MagazineEntity storedMagazine = loadMagazineById(storedId);
			return storedMagazine;

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}
	}
	
	@Override
	public void updateMagazine(MagazineEntity magazine) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("UPDATE building_system.magazine SET id_smeta = ?, location = ?, date_ = ?, smena = ?, weather = ?, conditions = ?, volume = ?, controle = ?, id_user = ? WHERE id = ?");
			Integer idSmeta = magazine.getIdSmeta();
			// для отображения полупустых записей в журнале
			if(idSmeta != null){
				statement.setInt(1, idSmeta);
			}else{
				statement.setString(1, null);
			}
			statement.setString(2, magazine.getLocation());
			statement.setDate(3, magazine.getDate());
			statement.setString(4, magazine.getSmena());
			statement.setString(5, magazine.getWeather());
			statement.setString(6, magazine.getConditions());
			// для отображения полупустых записей в журнале
			Double volume = magazine.getVolume();
			if(volume != null){
				statement.setDouble(7, volume);
			}else {
				statement.setString(7, null);
			}
			statement.setString(8, magazine.getControle());
			statement.setInt(9, magazine.getIdUser());
			statement.setInt(10, magazine.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}
	}
}