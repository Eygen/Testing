package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.StudentDao;
import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.service.GroupService;
import com.epam.zt.testing.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.epam.zt.testing.dao.Jdbc.SecureUtil.passwordToMd5;

public class JdbcStudentDao extends JdbcBaseDao<Student> implements StudentDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcStudentDao.class);
    private static final String FIND = "SELECT * FROM USER WHERE role_id = (SELECT id FROM ROLE WHERE name = 'student') AND deleted = FALSE";
    private static final String FIND_BY_GROUP = FIND + " AND group_id = ?";
    public static final String FIND_ALL = FIND + " ORDER BY id";
    public static final String FIND_BY_ID = FIND + " AND id = ?";
    public static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE login = ? AND password = ?";
    private static final String FIND_BY_NAME = FIND + " AND UPPER(LASTNAME) LIKE UPPER(?) AND UPPER(FIRSTNAME) LIKE UPPER(?)";
    private static final String FIND_BY_FIRSTNAME = FIND + " AND UPPER(firstname) LIKE UPPER(?)";
    private static final String FIND_BY_LASTNAME = FIND + " AND UPPER(lastname) LIKE UPPER(?)";
    public static final String CREATE = "INSERT INTO USER VALUES (DEFAULT, ?, FALSE, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE USER SET firstname = ?, lastname = ?, email = ?, password = ?, group_id = ? WHERE id = ?";
    public static final String DELETE = "UPDATE USER SET deleted = TRUE WHERE id = ?";
    private static final String REMOVE_GROUP = "UPDATE USER SET group_id = NULL WHERE id = ?";

    public JdbcStudentDao(Connection connection) {
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
    public Student parseResult(ResultSet result) {
        Student student;
        try {
            student = new Student((UUID) result.getObject("uuid"));
            student.setId(result.getInt("id"));
            student.setDeleted(result.getBoolean("deleted"));
            student.setFirstName(result.getString("firstname"));
            student.setLastName(result.getString("lastname"));
            student.setEmail(result.getString("email"));
            student.setLogin(result.getString("login"));
            student.setPassword(result.getString("password"));
            student.setRegisterDate(result.getDate("registerdate"));
            Role role = RoleService.findById(result.getInt("role_id"));
            student.setRole(role);
            if (result.getObject("group_id") == null) {
                student.setGroup(null);
            } else {
                Group group = GroupService.findById(result.getInt("group_id"));
                student.setGroup(group);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Student student) {
        try {
            statement.setObject(1, student.getUuid());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getLogin());
            String password = passwordToMd5(student.getPassword());
            statement.setString(6, password);
            statement.setDate(7, new Date(student.getRegisterDate().getTime()));
            statement.setInt(8, student.getRole().getId());
            statement.setInt(9, student.getGroup().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Student student) {
        try {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            String password = passwordToMd5(student.getPassword());
            statement.setString(4, password);
            statement.setInt(5, student.getGroup().getId());
            statement.setInt(6, student.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Student findStudent(String login, String password) {
        Student student;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, passwordToMd5(password));
            ResultSet result = statement.executeQuery();
            result.next();
            student = parseResult(result);
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }

    @Override
    public List<Student> findByGroup(Group group) throws DaoException {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_GROUP);
            statement.setInt(1, group.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Student student = new Student((UUID) result.getObject("uuid"));
                student.setId(result.getInt("id"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setGroup(group);
                students.add(student);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }

    @Override
    public List<Student> findByName(String lastname, String firstname) throws DaoException {
        List<Student> students = new ArrayList<>();
        try {
            lastname = "%" + lastname + "%";
            firstname = "%" + firstname + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, lastname);
            statement.setString(2, firstname);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                students.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }

    @Override
    public void removeGroup(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(REMOVE_GROUP);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Student> findByLastname(String lastName) throws DaoException {
        List<Student> students = new ArrayList<>();
        try {
            lastName = "%" + lastName + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_LASTNAME);
            statement.setString(1, lastName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                students.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }

    @Override
    public List<Student> findByFirstname(String firstName) throws DaoException {
        List<Student> students = new ArrayList<>();
        try {
            firstName = "%" + firstName + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_FIRSTNAME);
            statement.setString(1, firstName);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                students.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }

    @Override
    public Student findByFullName(String lastName, String firstName) throws DaoException {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND + " AND lastname = ? AND firstname = ?");
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                student = parseResult(result);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }
}
