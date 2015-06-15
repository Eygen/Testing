package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.TestDao;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.SubjectService;
import com.epam.zt.testing.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcTestDao extends JdbcBaseDao<Test> implements TestDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcTestDao.class);
    private static final String FIND = "SELECT * FROM TEST";
    private static final String FIND_BY_UUID = FIND + " WHERE uuid = ?";
    private static final String FIND_BY_SUBJECT = FIND + " WHERE subject_id = ?";
    private static final String FIND_BY_ID = FIND + " WHERE id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY id";
    private static final String DELETE = "DELETE FROM TEST WHERE id = ?";
    private static final String UPDATE = "UPDATE TEST SET subject_id = ?, question_amount = ? WHERE id = ?";
    private static final String CREATE = "INSERT INTO TEST VALUES(DEFAULT, ?, ?, ?, ?)";
    private static final String FIND_BY_AUTHOR = FIND + " WHERE author_id = ?";
    private static final String APPOINT_STUDENT = "INSERT INTO STUDENT_TEST VALUES (?, ?, DEFAULT )";
    private static final String BLOCK_TEST = "UPDATE STUDENT_TEST SET active = FALSE WHERE student_id = ? AND test_id = ?";
    private static final String UNBLOCK_TEST = "UPDATE STUDENT_TEST SET active = TRUE WHERE student_id = ? AND test_id = ?";
    private static final String FIND_BY_STUDENT = "SELECT * FROM TEST T JOIN STUDENT_TEST ST ON T.ID = ST.TEST_ID WHERE ST.STUDENT_ID = ?";
    private static final String FIND_PASSED_TESTS = "SELECT * FROM TEST T JOIN TEST_QUESTION TQ ON T.ID = TQ.TEST_ID WHERE TQ.student_id = ? ORDER BY T.ID";
    private static final String DELETE_STUDENT_TEST = "DELETE FROM STUDENT_TEST WHERE test_id = ?";
    private static final String FIND_STUDENT_TEST = "SELECT * FROM STUDENT_TEST WHERE student_id = ? AND test_id = ?";
    private static final String FIND_ACTIVE_TEST = FIND_STUDENT_TEST + " AND active = TRUE";
    private static final String DELETE_PASSED_TEST = "DELETE FROM STUDENT_TEST WHERE student_id = ? AND test_id = ?";
    private static final String FIND_SUBLIST = FIND + " ORDER BY id LIMIT ? OFFSET ?";

    public JdbcTestDao(Connection connection) {
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
    public Test parseResult(ResultSet result) {
        Test test;
        try {
            test = new Test((UUID) result.getObject("test.uuid"));
            test.setId(result.getInt("test.id"));
            Subject subject = SubjectService.findById(result.getInt("test.subject_id"));
            test.setSubject(subject);
            test.setQuestionAmount(result.getInt("test.question_amount"));
            Tutor tutor = TutorService.findById(result.getInt("test.author_id"));
            test.setAuthor(tutor);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return test;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Test test) {
        try {
            statement.setObject(1, test.getUuid());
            statement.setInt(2, test.getSubject().getId());
            statement.setInt(3, test.getQuestionAmount());
            statement.setInt(4, test.getAuthor().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Test test) {
        try {
            statement.setInt(1, test.getSubject().getId());
            statement.setInt(2, test.getQuestionAmount());
            statement.setInt(3, test.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Test> findByAuthor(Tutor tutor) {
        List<Test> tests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_AUTHOR);
            statement.setInt(1, tutor.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tests.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tests;
    }

    @Override
    public List<Test> findBySubject(Subject subject) throws DaoException {
        List<Test> tests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_SUBJECT);
            statement.setInt(1, subject.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tests.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tests;
    }

    @Override
    public List<Test> findByStudent(Student student) throws DaoException {
        List<Test> tests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_STUDENT);
            statement.setInt(1, student.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Test test = parseResult(result);
                test.setStudent(student);
                test.setActive(result.getBoolean("student_test.active"));
                tests.add(test);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tests;
    }

    @Override
    public boolean appointStudent(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(APPOINT_STUDENT);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            int count = statement.executeUpdate();
            statement.close();
            logger.info("Test {} is appointed to student {}", test, student);
            return count != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean blockTest(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(BLOCK_TEST);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            int count = statement.executeUpdate();
            statement.close();
            logger.info("Test {} is blocked for student {}", test, student);
            return count != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Test findByUuid(UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_UUID);
            statement.setObject(1, uuid);
            ResultSet result = statement.executeQuery();
            result.next();
            Test test = parseResult(result);
            result.close();
            statement.close();
            return test;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteStudentTest(Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_TEST);
            statement.setInt(1, test.getId());
            statement.executeUpdate();
            statement.close();
            logger.info("Appointed test was deleted {}", test);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkAppoint(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_TEST);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            ResultSet result = statement.executeQuery();
            boolean check = result.next();
            statement.close();
            result.close();
            return check;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void unblockTest(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UNBLOCK_TEST);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            statement.executeUpdate();
            statement.close();
            logger.info("Test {} is unblocked for student {}", test, student);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean checkActive(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_TEST);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            ResultSet result = statement.executeQuery();
            boolean check = result.next();
            statement.close();
            result.close();
            return check;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deletePassedTest(Student student, Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PASSED_TEST);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

        @Override
        public List<Test> findPassedTests(Student student) throws DaoException {
            List<Test> tests = new ArrayList<>();
            try {
                PreparedStatement statement = connection.prepareStatement(FIND_PASSED_TESTS);
                statement.setInt(1, student.getId());
                ResultSet result = statement.executeQuery();
                result.next();
                Test test = parseResult(result);
                int id = test.getId();
                tests.add(test);
                while (result.next()) {
                    Test newTest = parseResult(result);
                    if (id != newTest.getId()) {
                        id = newTest.getId();
                        tests.add(newTest);
                    }
                }
                result.close();
                statement.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return tests;
        }

    @Override
    public List<Test> findSublist(int rowcount, int firstrow) throws DaoException {
        List<Test> tests = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_SUBLIST);
            statement.setInt(1, rowcount);
            statement.setInt(2, firstrow);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tests.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tests;
    }


}
