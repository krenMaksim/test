package com.mkren.building.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.dao.SmetaDAO;
import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.dao.mysql.db.ConnectionPool;
import com.mkren.building.dao.mysql.db.ResultSetConverter;
import com.mkren.building.entity.SmetaEntity;

public class SmetaDaoImpl extends BaseDao implements SmetaDAO {
	
	@Override
	public List<SmetaEntity> loadAllSmeta() throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		List<SmetaEntity> result = new ArrayList<SmetaEntity>();

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT * from building_system.smeta");
			set = statement.executeQuery();

			while (set.next()) {
				SmetaEntity entity = ResultSetConverter.createSmetaEntity(set);
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
	public SmetaEntity loadSmetaById(Integer smetaId) throws DaoException {
		if (smetaId == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.smeta WHERE id=?");
			statement.setInt(1, smetaId);

			set = statement.executeQuery();

			if (set.next()) {
				SmetaEntity entity = ResultSetConverter.createSmetaEntity(set);
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
	public Integer loadIdByPp(Integer smetaPP) throws DaoException {
		if (smetaPP == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.smeta WHERE pp=?");
			statement.setInt(1, smetaPP);

			set = statement.executeQuery();

			if (set.next()) {
				SmetaEntity entity = ResultSetConverter.createSmetaEntity(set);
				return entity.getId();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return null;
	}

	@Override
	public Double getRestFromVolumesById(Integer smetaId) throws DaoException {
		if (smetaId == null) {
			return null;
		}
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		Double result = null;

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT (SELECT SUM(smeta.kol_vo) FROM building_system.smeta WHERE smeta.id = ?)-SUM(magazine.volume) AS rest "
					+ "FROM building_system.magazine WHERE magazine.id_smeta = ?");
			statement.setInt(1, smetaId);
			statement.setInt(2, smetaId);
			set = statement.executeQuery();

			if(set.next()) {
				result = set.getDouble("rest");				
			}
			
			if(result == null){
				return null;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return result;
	}
}
