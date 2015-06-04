package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.GroupDao;
import com.epam.zt.testing.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JdbcGroupDao extends JdbcBaseDao<Group> implements GroupDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcGroupDao.class);
    private static final String FIND = "SELECT * FROM GROUP_TABLE WHERE deleted = FALSE";
    private static final String FIND_BY_ID = FIND + " AND id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String DELETE = "UPDATE GROUP_TABLE SET deleted = TRUE WHERE id = ?";
    private static final String UPDATE = "UPDATE GROUP_TABLE SET name = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO GROUP_TABLE VALUES(DEFAULT, ?, FALSE, ?)";
    private static final String FIND_BY_NAME = FIND + " AND name = ?";


    public JdbcGroupDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery(Select select) {
        switch (select) {
            case ID:
                return FIND_BY_ID;
            case ALL:
                return FIND_ALL;
        }
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    public String getCreateQuery() {
        return CREATE;
    }

    @Override
    public Group parseResult(ResultSet result) throws DaoException {
        Group group;
        try {
            group = new Group((UUID) result.getObject("uuid"));
            group.setId(result.getInt("id"));
            group.setDeleted(result.getBoolean("deleted"));
            group.setName(result.getString("name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Group group) throws DaoException {
        try {
            statement.setObject(1, group.getUuid());
            statement.setString(2, group.getName());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Group group) throws DaoException {
        try {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Group findByName(String groupName) {
        Group group = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, groupName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                group = parseResult(result);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }
}
