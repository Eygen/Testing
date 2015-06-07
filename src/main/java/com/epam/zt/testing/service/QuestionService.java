package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.QuestionDao;
import com.epam.zt.testing.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class QuestionService {

    public static void createQuestion(Question question) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        questionDao.create(question);
        Question createdQuestion = questionDao.findByUuid(question.getUuid());
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            answer.setQuestion(createdQuestion);
            questionDao.createAnswer(answer);
        }
        questionDao.close();
    }

    public static List<Question> findStudentResult(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        List<Question> questions = questionDao.findStudentResult(student, test);
        questionDao.close();
        return questions;
    }

    public static void deleteQuestion(Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        questionDao.deleteAnswer(test);
        questionDao.deleteQuestion(test);
        questionDao.close();
    }

    public static List<Question> getQuestions(Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        List<Question> questions = questionDao.getQuestions(test);
        for (Question question : questions) {
            List<Answer> answers = questionDao.getAnswers(question);
            question.setAnswers(answers);
        }
        questionDao.close();
        return questions;
    }

    public static Answer findAnswer(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        Answer answer = questionDao.findAnswer(id);
        questionDao.close();
        return answer;
    }

    public static void createStudentResult(Student student, Test test, Question question, Answer answer) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        questionDao.createStudentResult(student, test, question, answer);
        questionDao.close();
    }

    public static List<Test> findPassedTests(Student student, Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        QuestionDao questionDao = factory.getQuestionDao();
        List<Subject> subjects = SubjectService.findByTutor(tutor);
        List<Test> tutorTests = new ArrayList<>();
        for (Subject subject : subjects) {
            List<Test> bySubject = TestService.findBySubject(subject);
            for (Test test : bySubject) {
                tutorTests.add(test);
            }
        }
        List<Test> passedTests = new ArrayList<>();
        for (Test tutorTest : tutorTests) {
            List<Question> questions = questionDao.findStudentResult(student, tutorTest);
            if (questions.size() != 0) {
                tutorTest.setQuestions(questions);
                tutorTest.setStudent(student);
                tutorTest.setAuthor(tutor);
                passedTests.add(tutorTest);
            }
        }
        questionDao.close();
        return passedTests;
    }

}
