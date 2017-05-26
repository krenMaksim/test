package com.mkren.building.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mkren.building.dao.UserDAO;
import com.mkren.building.dao.exeptions.DaoException;
import com.mkren.building.dao.mysql.db.ConnectionPool;
import com.mkren.building.dao.mysql.db.ResultSetConverter;
import com.mkren.building.entity.UserEntity;

public class UserDaoImpl extends BaseDao implements UserDAO{
	
	@Override
	public List<UserEntity> loadAllUsers() throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		List<UserEntity> result = new ArrayList<UserEntity>();

		try {
			connection = ConnectionPool.getPool().getConnection();
			statement = connection.prepareStatement("SELECT * from building_system.users");
			set = statement.executeQuery();

			while (set.next()) {
				UserEntity entity = ResultSetConverter.createUserEntity(set);
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
	public UserEntity loadUserById(Integer userId) throws DaoException {
		if (userId == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.users WHERE id=?");
			statement.setInt(1, userId);

			set = statement.executeQuery();

			if (set.next()) {
				UserEntity entity = ResultSetConverter.createUserEntity(set);
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
	public UserEntity loadUserByLogin(String login) throws DaoException {
		if (login == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.users WHERE login=?");
			statement.setString(1, login);

			set = statement.executeQuery();

			if (set.next()) {
				UserEntity entity = ResultSetConverter.createUserEntity(set);
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
	public UserEntity loadUserBySurname(String surname) throws DaoException {
		if (surname == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			connection = ConnectionPool.getPool().getConnection();

			statement = connection.prepareStatement("SELECT * FROM building_system.users WHERE surname_initials=?");
			statement.setString(1, surname);

			set = statement.executeQuery();

			if (set.next()) {
				UserEntity entity = ResultSetConverter.createUserEntity(set);
				return entity;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			ConnectionPool.getPool().closeDbResources(connection, statement, set);
		}

		return null;
	}
}
