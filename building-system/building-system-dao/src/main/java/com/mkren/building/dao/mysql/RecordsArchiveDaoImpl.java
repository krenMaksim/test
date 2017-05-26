package com.mkren.building.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.dao.RecordsArchiveDAO;
import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.dao.mysql.db.ConnectionPool;
import com.mkren.building.dao.mysql.db.ResultSetConverter;
import com.mkren.building.entity.RecordsArchiveEntity;

public class RecordsArchiveDaoImpl extends BaseDao implements RecordsArchiveDAO {
	
	@Override
	public RecordsArchiveEntity loadRecordsArchiveById(Integer recordId) throws DaoException {
		if (recordId == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.records_archive WHERE id=?");
			statement.setInt(1, recordId);

			set = statement.executeQuery();

			if (set.next()) {
				RecordsArchiveEntity entity = ResultSetConverter.createRecordsArchiveEntity(set);
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
	public RecordsArchiveEntity storeRecordsArchive(RecordsArchiveEntity record) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("INSERT INTO building_system.records_archive (id_mag, date_, name_column, old_record, id_user) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, record.getIdMag());
			statement.setDate(2, record.getDate());
			statement.setString(3, record.getNameColumn());
			statement.setString(4, record.getOldRecord());
			statement.setInt(5, record.getIdUser());

			statement.executeUpdate();

			Integer storedId = loadStoredId(statement);
			RecordsArchiveEntity storedRecord = loadRecordsArchiveById(storedId);
			return storedRecord;

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}
	}
	
	@Override
	public List<RecordsArchiveEntity> loadRecordsArchiveByMagazineId(Integer magazineId) throws DaoException {
		if (magazineId == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		List<RecordsArchiveEntity> result = new ArrayList<RecordsArchiveEntity>();

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.records_archive WHERE id_mag=?");
			statement.setInt(1, magazineId);

			set = statement.executeQuery();

			while (set.next()) {
				RecordsArchiveEntity entity = ResultSetConverter.createRecordsArchiveEntity(set);
				result.add(entity);
			}
			
			return result;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}
		
	}
}