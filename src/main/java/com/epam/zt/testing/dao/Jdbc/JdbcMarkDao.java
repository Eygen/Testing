package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.MarkDao;
import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcMarkDao extends JdbcBaseDao<Mark> implements MarkDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcMarkDao.class);
    private static final String FIND = "SELECT * FROM MARK";
    private static final String FIND_BY_ID = FIND + " WHERE id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String DELETE = "DELETE FROM MARK WHERE id = ?";
    private static final String UPDATE = "UPDATE MARK SET value = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO MARK VALUES(DEFAULT, ?, ?, ?, ?)";
    private static final String FIND_SUBLIST = FIND + " WHERE student_id = ? ORDER BY id LIMIT ? OFFSET ?";

    public JdbcMarkDao(Connection connection) {
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
    public Mark parseResult(ResultSet result) throws DaoException {
        Mark mark;
        try {
            mark = new Mark((UUID) result.getObject("uuid"));
            mark.setId(result.getInt("id"));
            Student student = new JdbcStudentDao(connection).findById(result.getInt("student_id"));
            mark.setStudent(student);
            Test test = new JdbcTestDao(connection).findById(result.getInt("test_id"));
            mark.setTest(test);
            mark.setValue(result.getInt("value"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return mark;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Mark mark) throws DaoException {
        try {
            statement.setObject(1, mark.getUuid());
            statement.setInt(2, mark.getStudent().getId());
            statement.setInt(3, mark.getTest().getId());
            statement.setInt(4, mark.getValue());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Mark mark) throws DaoException {
        try {
            statement.setInt(1, mark.getValue());
            statement.setInt(2, mark.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Mark> getMarks(Student student) throws DaoException {
        List<Mark> marks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM MARK WHERE student_id = ?");
            statement.setInt(1, student.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                marks.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marks;
    }

    @Override
    public Mark findByUuid(UUID uuid) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND + " WHERE uuid = ?");
            statement.setObject(1, uuid);
            ResultSet result = statement.executeQuery();
            result.next();
            Mark mark = parseResult(result);
            result.close();
            statement.close();
            return mark;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Mark findMark(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND + " WHERE student_id = ? AND test_id = ?");
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            ResultSet result = statement.executeQuery();
            result.next();
            Mark mark = parseResult(result);
            result.close();
            statement.close();
            return mark;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Mark> findSublist(Student student, int rowcount, int firstrow) throws DaoException {
        List<Mark> marks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_SUBLIST);
            statement.setInt(1, student.getId());
            statement.setInt(2, rowcount);
            statement.setInt(3, firstrow);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                marks.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marks;
    }
}
