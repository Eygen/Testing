package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.RoleDao;
import com.epam.zt.testing.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JdbcRoleDao extends JdbcBaseDao<Role> implements RoleDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcRoleDao.class);
    private static final String FIND = "SELECT * FROM ROLE";
    private static final String FIND_BY_ID = FIND + " WHERE id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String DELETE = "DELETE FROM ROLE WHERE id = ?";
    private static final String UPDATE = "UPDATE ROLE SET name = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO ROLE VALUES(DEFAULT, ?, ?)";
    private static final String FIND_BY_NAME = FIND + " WHERE name = ?";

    public JdbcRoleDao(Connection connection) {
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
    public Role parseResult(ResultSet result) throws DaoException {
        Role role;
        try {
            role = new Role((UUID) result.getObject("uuid"));
            role.setId(result.getInt("id"));
            role.setName(result.getString("name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return role;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Role role) throws DaoException {
        try {
            statement.setObject(1, role.getUuid());
            statement.setString(2, role.getName());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Role role) throws DaoException {
        try {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Role findByName(String roleName) {
        Role role;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, roleName);
            ResultSet result = statement.executeQuery();
            result.next();
            role = parseResult(result);
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return role;
    }
}
