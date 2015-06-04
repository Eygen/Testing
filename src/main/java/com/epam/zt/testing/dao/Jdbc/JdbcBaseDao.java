package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.Dao;
import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.zt.testing.dao.Jdbc.JdbcBaseDao.Select.ALL;
import static com.epam.zt.testing.dao.Jdbc.JdbcBaseDao.Select.ID;

public abstract class JdbcBaseDao<T extends BaseEntity> implements Dao<T> {
    private static final Logger logger = LoggerFactory.getLogger(JdbcBaseDao.class);
    protected Connection connection;

    public JdbcBaseDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery(Select select);

    public abstract String getDeleteQuery();

    public abstract String getUpdateQuery();

    public abstract String getCreateQuery();

    public abstract T parseResult(ResultSet result);

    public abstract void prepareForInsert(PreparedStatement statement, T entity);

    public abstract void prepareForUpdate(PreparedStatement statement, T entity);

    public T findById(Integer id) throws DaoException {
        String sql = getSelectQuery(ID);
        T entity;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            entity = parseResult(result);
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    public List<T> findAll() throws DaoException {
        List<T> entities = new ArrayList<>();
        String sql = getSelectQuery(ALL);
        try (
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)
        ) {
            while (result.next()) {
                entities.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

    public boolean delete(T entity) throws DaoException {
        if (findById(entity.getId()) == null) {
            throw new DaoException("No such object for deleting!");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(getDeleteQuery());
            statement.setInt(1, entity.getId());
            int result = statement.executeUpdate();
            if (result > 1) {
                throw new DaoException("Deleted more than one object: " + result);
            }
            logger.info("Object is deleted " + entity);
            statement.close();
            return result != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean create(T entity) throws DaoException {
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareForInsert(statement, entity);
            int count = statement.executeUpdate();
            if (count > 1) {
                throw new DaoException("Created more than one object: " + count);
            } else if (count == 0) {
                throw new DaoException("Object was not created!");
            }
            logger.info("Object is created " + entity);
            statement.close();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean update(T entity) throws DaoException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareForUpdate(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Updated more than one object: " + count);
            }
            logger.info("Object is updated " + entity);
            statement.close();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void close() {
        JdbcDaoFactory.getInstance().close(this.connection);
    }

    public enum Select {
        ALL, ID
    }

}
