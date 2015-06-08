package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Answer;
import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;

import java.util.List;
import java.util.UUID;

public interface QuestionDao extends Dao<Question> {
    void createAnswer(Answer answer) throws DaoException;

    Question findByUuid(UUID uuid) throws DaoException;

    List<Question> findStudentResult(Student student, Test test) throws DaoException;

    void deleteAnswer(Test test) throws DaoException;

    void deleteQuestion(Test test) throws DaoException;

    List<Question> getQuestions(Test test) throws DaoException;

    List<Answer> getAnswers(Question question) throws DaoException;

    Answer findAnswer(int id) throws DaoException;

    void createStudentResult(Student student, Test test, Question question, Answer answer) throws DaoException;

    void updateAnswer(Answer answer) throws DaoException;
}
