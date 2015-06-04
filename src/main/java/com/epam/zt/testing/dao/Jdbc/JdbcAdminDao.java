package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.AdminDao;
import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.model.Admin;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.UUID;

import static com.epam.zt.testing.dao.Jdbc.SecureUtil.passwordToMd5;

public class JdbcAdminDao extends JdbcBaseDao<Admin> implements AdminDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcAdminDao.class);
    private static final String FIND = "SELECT * FROM USER WHERE role_id = (SELECT id FROM ROLE WHERE name = 'admin') AND deleted = FALSE";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String FIND_BY_ID = FIND + " AND id = ?";
    public static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE login = ? AND password = ?";
    private static final String CREATE = "INSERT INTO USER VALUES (DEFAULT, ?, FALSE, ?, ?, ?, ?, ?, ?, ?, NULL)";
    private static final String UPDATE = "UPDATE USER SET firstname = ?, lastname = ?, email = ?, password = ? WHERE role_id = (SELECT id FROM ROLE WHERE name = 'admin') AND id = ?";
    private static final String DELETE = "UPDATE USER SET deleted = TRUE WHERE id = ?";

    public JdbcAdminDao(Connection connection) {
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
    public Admin parseResult(ResultSet result) {
        Admin admin;
        try {
            admin = new Admin((UUID) result.getObject("uuid"));
            admin.setId(result.getInt("id"));
            admin.setDeleted(result.getBoolean("deleted"));
            admin.setFirstName(result.getString("firstname"));
            admin.setLastName(result.getString("lastname"));
            admin.setEmail(result.getString("email"));
            admin.setLogin(result.getString("login"));
            admin.setPassword(result.getString("password"));
            admin.setRegisterDate(result.getDate("registerdate"));
            Role role = RoleService.findById(result.getInt("role_id"));
            admin.setRole(role);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Admin admin) {
        try {
            statement.setObject(1, admin.getUuid());
            statement.setString(2, admin.getFirstName());
            statement.setString(3, admin.getLastName());
            statement.setString(4, admin.getEmail());
            statement.setString(5, admin.getLogin());
            String password = passwordToMd5(admin.getPassword());
            statement.setString(6, password);
            statement.setDate(7, new Date(admin.getRegisterDate().getTime()));
            statement.setInt(8, admin.getRole().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Admin admin) {
        try {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            String password = passwordToMd5(admin.getPassword());
            statement.setString(4, password);
            statement.setInt(5, admin.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Admin findAdmin(String login, String password) {
        Admin admin;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, SecureUtil.passwordToMd5(password));
            ResultSet result = statement.executeQuery();
            result.next();
            admin = parseResult(result);
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }
}
