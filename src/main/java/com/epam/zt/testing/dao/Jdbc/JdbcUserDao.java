package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.UserDao;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.epam.zt.testing.dao.Jdbc.SecureUtil.passwordToMd5;

public class JdbcUserDao extends JdbcBaseDao<User> implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
    private static final String FIND = "SELECT * FROM USER WHERE role_id = (SELECT id FROM ROLE WHERE name = 'user')";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM USER WHERE id = ?";
    private static final String CREATE = "INSERT INTO USER VALUES (DEFAULT, ?, FALSE, ?, ?, ?, ?, ?, ?, ?, NULL)";
    private static final String UPDATE = "UPDATE USER SET role_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM USER WHERE id = ?";
    public static final String EXIST_LOGIN = "SELECT * FROM USER WHERE DELETED = FALSE AND login = ?";
    public static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE DELETED = FALSE AND login = ? AND password = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM USER WHERE DELETED = FALSE AND UPPER(LASTNAME) LIKE UPPER(?) AND UPPER(FIRSTNAME) LIKE UPPER(?)";
    private static final String FIND_SUBLIST = "SELECT * FROM USER WHERE deleted = FALSE ORDER BY id LIMIT ? OFFSET ?";
    private static final String FIND_BY_FIRSTNAME = "SELECT * FROM USER WHERE DELETED = FALSE AND UPPER(firstname) LIKE UPPER(?)";
    private static final String FIND_BY_LASTNAME = "SELECT * FROM USER WHERE DELETED = FALSE AND UPPER(lastname) LIKE UPPER(?)";

    public JdbcUserDao(Connection connection) {
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
    public User parseResult(ResultSet result) {
        User user;
        try {
            user = new User((UUID) result.getObject("uuid"));
            user.setId(result.getInt("id"));
            user.setDeleted(false);
            user.setFirstName(result.getString("firstname"));
            user.setLastName(result.getString("lastname"));
            user.setEmail(result.getString("email"));
            user.setLogin(result.getString("login"));
            user.setPassword(result.getString("password"));
            user.setRegisterDate(result.getDate("registerdate"));
            Role role = RoleService.findById(result.getInt("role_id"));
            user.setRole(role);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, User user) {
        try {
            statement.setObject(1, user.getUuid());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getLogin());
            String password = passwordToMd5(user.getPassword());
            statement.setString(6, password);
            statement.setDate(7, new Date(user.getRegisterDate().getTime()));
            statement.setInt(8, user.getRole().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, User user) {
        try {
            statement.setInt(1, user.getRole().getId());
            statement.setInt(2, user.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Role findUser(String login, String password) throws DaoException {
        Role role = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, SecureUtil.passwordToMd5(password));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role = RoleService.findById(result.getInt("role_id"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return role;
    }

    @Override
    public boolean existLogin(String login) throws DaoException {
        boolean exist;
        try {
            PreparedStatement statement = connection.prepareStatement(EXIST_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            exist = result.next();
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return exist;
    }

    @Override
    public void close() {
        JdbcDaoFactory.getInstance().close(this.connection);
    }

    @Override
    public List<User> findByName(String lastName, String firstName) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            lastName = "%" + lastName + "%";
            firstName = "%" + firstName + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void deleteChild(User user) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE USER SET deleted = TRUE WHERE id = ?");
            statement.setInt(1, user.getId());
            int result = statement.executeUpdate();
            if (result > 1) {
                throw new DaoException("Deleted more than one object: " + result);
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findSublist(int rowcount, int firstrow) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_SUBLIST);
            statement.setInt(1, rowcount);
            statement.setInt(2, firstrow);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findByLastname(String lastName) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            lastName = "%" + lastName + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LASTNAME);
            statement.setString(1, lastName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findByFirstname(String firstName) throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            firstName = "%" + firstName + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_FIRSTNAME);
            statement.setString(1, firstName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

}
