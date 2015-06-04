package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoException;
import com.epam.zt.testing.dao.QuestionDao;
import com.epam.zt.testing.model.Answer;
import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcQuestionDao extends JdbcBaseDao<Question> implements QuestionDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcQuestionDao.class);
    private static final String FIND = "SELECT * FROM QUESTION";
    private static final String FIND_BY_ID = FIND + " WHERE id = ?";
    private static final String FIND_ALL = FIND + " ORDER BY test_id";
    private static final String FIND_BY_TEST = FIND + " WHERE test_id = ?";
    private static final String DELETE_QUESTION = "DELETE FROM QUESTION WHERE test_id = ?";
    private static final String DELETE_ANSWER = "DELETE FROM ANSWER WHERE test_id = ?";
    private static final String UPDATE = "UPDATE QUESTION SET description = ? WHERE id = ?";
    private static final String CREATE_QUESTION = "INSERT INTO QUESTION VALUES(DEFAULT, ?, ?, ?)";
    private static final String CREATE_ANSWER = "INSERT INTO ANSWER VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_UUID = "SELECT * FROM QUESTION WHERE uuid = ?";
    private static final String FIND_STUDENT_RESULT = "SELECT * FROM QUESTION Q JOIN TEST_QUESTION TQ ON Q.ID = TQ.QUESTION_ID WHERE TQ.student_id = ? AND TQ.test_id = ?";
    private static final String FIND_BY_QUESTION = "SELECT * FROM ANSWER WHERE question_id = ?";
    private static final String FIND_ANSWER = "SELECT * FROM ANSWER WHERE id = ?";
    private static final String CREATE_STUDENT_RESULT = "INSERT INTO TEST_QUESTION VALUES (?, ?, ?, ?)";

    public JdbcQuestionDao(Connection connection) {
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
        return DELETE_QUESTION;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUESTION;
    }

    @Override
    public Question parseResult(ResultSet result) {
        Question question;
        try {
            question = new Question((UUID) result.getObject("question.uuid"));
            question.setId(result.getInt("question.id"));
            question.setDescription(result.getString("question.description"));
            Test test = TestService.findById(result.getInt("question.test_id"));
            question.setTest(test);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return question;
    }

    private Answer parseAnswer(ResultSet result) {
        Answer answer;
        try {
            answer = new Answer((UUID) result.getObject("answer.uuid"));
            answer.setId(result.getInt("answer.id"));
            Question question = findById(result.getInt("answer.question_id"));
            answer.setQuestion(question);
            answer.setTest(TestService.findById(result.getInt("test_id")));
            answer.setDescription(result.getString("answer.description"));
            answer.setCorrect(result.getBoolean("answer.correct"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return answer;
    }

    @Override
    public void prepareForInsert(PreparedStatement statement, Question question) {
        try {
            statement.setObject(1, question.getUuid());
            statement.setString(2, question.getDescription());
            statement.setInt(3, question.getTest().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void prepareForUpdate(PreparedStatement statement, Question question) {
        try {
            statement.setString(1, question.getDescription());
            statement.setInt(2, question.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Question> getQuestions(Test test) {
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_TEST);
            statement.setInt(1, test.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                questions.add(parseResult(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return questions;
    }

    @Override
    public List<Answer> getAnswers(Question question) throws DaoException {
        List<Answer> answers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_QUESTION);
            statement.setInt(1, question.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                answers.add(parseAnswer(result));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return answers;
    }

    @Override
    public void createAnswer(Answer answer) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_ANSWER);
            statement.setObject(1, answer.getUuid());
            statement.setInt(2, answer.getQuestion().getId());
            statement.setInt(3, answer.getTest().getId());
            statement.setString(4, answer.getDescription());
            statement.setBoolean(5, answer.isCorrect());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Question findByUuid(UUID uuid) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_UUID);
            statement.setObject(1, uuid);
            ResultSet result = statement.executeQuery();
            result.next();
            Question question = parseResult(result);
            result.close();
            statement.close();
            return question;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Question> findStudentResult(Student student, Test test) throws DaoException {
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_RESULT);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Question question = new Question((UUID) result.getObject("question.uuid"));
                question.setId(result.getInt("question.id"));
                question.setDescription(result.getString("question.description"));
                question.setTest(test);
                List<Answer> answers = new ArrayList<>();
                Answer answer = findAnswer(result.getInt("test_question.answer_id"));
                answers.add(answer);
                question.setAnswers(answers);
                questions.add(question);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return questions;
    }

    @Override
    public Answer findAnswer(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ANSWER);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            Answer answer = parseAnswer(result);
            result.close();
            statement.close();
            return answer;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createStudentResult(Student student, Test test, Question question, Answer answer) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_STUDENT_RESULT);
            statement.setInt(1, student.getId());
            statement.setInt(2, test.getId());
            statement.setInt(3, question.getId());
            statement.setInt(4, answer.getId());
            statement.executeUpdate();
            statement.close();
            logger.info("Student {} passed test {}", student, test);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteQuestion(Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUESTION);
            statement.setInt(1, test.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteAnswer(Test test) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ANSWER);
            statement.setInt(1, test.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
