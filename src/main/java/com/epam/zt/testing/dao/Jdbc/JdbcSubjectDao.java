package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.SubjectDao;
import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcSubjectDao extends JdbcBaseDao<Subject> implements SubjectDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcSubjectDao.class);
    private static final String FIND = "SELECT * FROM SUBJECT";
    private static final String FIND_BY_NAME = FIND + " WHERE UPPER(name) LIKE UPPER(?)";
    private static final String FIND_BY_TUTOR = FIND + " WHERE tutor_id = ?";
    private static final String FIND_BY_ID = FIND + " WHERE id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String DELETE = "DELETE FROM SUBJECT WHERE id = ?";
    private static final String UPDATE = "UPDATE SUBJECT SET name = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO SUBJECT VALUES(DEFAULT, ?, ?, ?)";
    private static final String FIND_BY_FULLNAME = FIND + " WHERE name = ?";
    private static final String FIND_SUBLIST = FIND + " ORDER BY id LIMIT ? OFFSET ?";

    public JdbcSubjectDao(Connection connection) {
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
    public Subject parseResult(ResultSet result) {
        Subject subject;
        try {
            subject = new Subject((UUID) result.getObject("uuid"));
            subject.setId(result.getInt("id"));
            subject.setName(result.getString("name"));
            Tutor tutor = new JdbcTutorDao(connection).findById(result.getInt("tutor_id"));
            subject.setTutor(tutor);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return subject;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Subject subject) {
        try {
            statement.setObject(1, subject.getUuid());
            statement.setString(2, subject.getName());
            statement.setInt(3, subject.getTutor().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Subject subject) {
        try {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Subject> findByTutor(Tutor tutor) throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_TUTOR);
            statement.setInt(1, tutor.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                subjects.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return subjects;
    }

    @Override
    public List<Subject> findByName(String name) throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        try {
            name = "%" + name + "%";
            PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                subjects.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return subjects;
    }

    @Override
    public Subject findByFullName(String name) throws DaoException {
        Subject subject = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_FULLNAME);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                subject = parseResult(result);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return subject;
    }

    @Override
    public List<Subject> findSublist(int rowcount, int firstrow) throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_SUBLIST);
            statement.setInt(1, rowcount);
            statement.setInt(2, firstrow);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                subjects.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return subjects;
    }
}
