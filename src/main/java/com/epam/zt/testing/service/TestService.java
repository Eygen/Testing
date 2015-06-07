package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.TestDao;
import com.epam.zt.testing.model.*;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class TestService {

    public static List<Test> findByAuthor(Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        List<Test> tests = testDao.findByAuthor(tutor);
        testDao.close();
        return tests;
    }

    public static List<Test> findBySubject(Subject subject) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        List<Test> tests = testDao.findBySubject(subject);
        testDao.close();
        return tests;
    }

    public static List<Test> findByStudent(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        List<Test> tests = testDao.findByStudent(student);
        testDao.close();
        return tests;
    }

    public static void blockTest(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        testDao.blockTest(student, test);
        testDao.close();
    }

    public static Test findById(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        Test test = testDao.findById(id);
        testDao.close();
        return test;
    }

    public static void deleteTest(Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        testDao.deleteStudentTest(test);
        QuestionService.deleteQuestion(test);
        testDao.delete(test);
        QuestionService.deleteQuestion(test);
        testDao.close();
    }

    public static void deleteStudentTest(Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        testDao.deleteStudentTest(test);
        testDao.close();
    }

    public static Test createTest(Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        testDao.create(test);
        Test createdTest = testDao.findByUuid(test.getUuid());
        testDao.close();
        return createdTest;
    }

    public static void assignTest(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        if (testDao.checkAppoint(student, test)) {
            testDao.unblockTest(student, test);
        } else {
            testDao.appointStudent(student, test);
        }
        testDao.close();
    }

    public static boolean checkAppoint(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        boolean check = testDao.checkAppoint(student, test);
        testDao.close();
        return check;
    }

    public static boolean checkActive(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        boolean check = testDao.checkActive(student, test);
        testDao.close();
        return check;
    }

    public static void deletePassedTest(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        testDao.deletePassedTest(student, test);
        testDao.close();
    }

    public static List<Test> findPassedTests(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        List<Test> tests = testDao.findPassedTests(student);
        for (Test test : tests) {
            Mark mark = MarkService.findMark(student, test);
            test.setMark(mark);
        }
        testDao.close();
        return tests;
    }

    public static List<Test> findSublist(int rowcount, int firstrow) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TestDao testDao = factory.getTestDao();
        List<Test> tests = testDao.findSublist(rowcount, firstrow);
        testDao.close();
        return tests;
    }
}
