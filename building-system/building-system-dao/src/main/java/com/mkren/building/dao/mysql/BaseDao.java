package com.mkren.building.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.dao.mysql.db.ConnectionPool;

public abstract class BaseDao {

	protected Integer loadStoredId(Statement statement) throws SQLException {
		ResultSet set = null;

		try {
			set = statement.getGeneratedKeys();
			
			if (set.next()) {
				Integer storedId = set.getInt(1);
				return storedId;
			}
		} finally { 
			if (set != null) {
				set.close();
			}
		}

		return null;
	}
	
	protected void removeById(Integer id, String nameTable) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("DELETE FROM building_system." + nameTable + " WHERE id = ?");
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement);
		}
	}
}
