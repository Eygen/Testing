package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.TutorDao;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.UUID;

import static com.epam.zt.testing.dao.Jdbc.SecureUtil.passwordToMd5;

public class JdbcTutorDao extends JdbcBaseDao<Tutor> implements TutorDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTutorDao.class);
    private static final String FIND = "SELECT * FROM USER WHERE role_id = (SELECT id FROM ROLE WHERE name = 'tutor') AND deleted = FALSE";
    private static final String FIND_BY_NAME = FIND + " AND lastname = ? AND firstname = ?";
    private static final String FIND_BY_ID = FIND + " AND id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    public static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE login = ? AND password = ?";
    private static final String DELETE = "UPDATE USER SET deleted = TRUE WHERE id = ?";
    private static final String UPDATE = "UPDATE USER SET firstname = ?, lastname = ?, email = ?, password = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO USER VALUES(DEFAULT, ?, FALSE, ?, ?, ?, ?, ?, ?, ?, NULL)";

    public JdbcTutorDao(Connection connection) {
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
    public Tutor parseResult(ResultSet result) throws DaoException {
        Tutor tutor;
        try {
            tutor = new Tutor((UUID) result.getObject("uuid"));
            tutor.setId(result.getInt("id"));
            tutor.setDeleted(result.getBoolean("deleted"));
            tutor.setFirstName(result.getString("firstname"));
            tutor.setLastName(result.getString("lastname"));
            tutor.setEmail(result.getString("email"));
            tutor.setLogin(result.getString("login"));
            tutor.setPassword(result.getString("password"));
            tutor.setRegisterDate(result.getDate("registerdate"));
            Role role = RoleService.findById(result.getInt("role_id"));
            tutor.setRole(role);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tutor;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Tutor tutor) throws DaoException {
        try {
            statement.setObject(1, tutor.getUuid());
            statement.setString(2, tutor.getFirstName());
            statement.setString(3, tutor.getLastName());
            statement.setString(4, tutor.getEmail());
            statement.setString(5, tutor.getLogin());
            String password = passwordToMd5(tutor.getPassword());
            statement.setString(6, password);
            statement.setDate(7, new Date(tutor.getRegisterDate().getTime()));
            statement.setInt(8, tutor.getRole().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Tutor tutor) throws DaoException {
        try {
            statement.setString(1, tutor.getFirstName());
            statement.setString(2, tutor.getLastName());
            statement.setString(3, tutor.getEmail());
            statement.setString(4, tutor.getPassword());
            statement.setInt(5, tutor.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Tutor findTutor(String login, String password) {
        Tutor tutor;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, SecureUtil.passwordToMd5(password));
            ResultSet result = statement.executeQuery();
            result.next();
            tutor = parseResult(result);
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tutor;
    }

    @Override
    public Tutor findByName(String lastname, String firstname) throws DaoException {
        Tutor tutor = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, lastname);
            statement.setString(2, firstname);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                tutor = parseResult(result);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tutor;
    }
}
