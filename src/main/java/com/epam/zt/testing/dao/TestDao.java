package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.model.Tutor;

import java.util.List;
import java.util.UUID;

public interface TestDao extends Dao<Test> {
    List<Test> findByAuthor(Tutor tutor) throws DaoException;

    List<Test> findBySubject(Subject subject) throws DaoException;

    List<Test> findByStudent(Student student) throws DaoException;

    boolean appointStudent(Student student, Test test) throws DaoException;

    boolean blockTest(Student student, Test test) throws DaoException;

    Test findByUuid(UUID uuid) throws DaoException;

    void deleteStudentTest(Test test) throws DaoException;

    boolean checkAppoint(Student student, Test test) throws DaoException;

    void unblockTest(Student student, Test test) throws DaoException;

    boolean checkActive(Student student, Test test) throws DaoException;

    void deletePassedTest(Student student, Test test) throws DaoException;

    List<Test> findPassedTests(Student student) throws DaoException;

    List<Test> findSublist(int rowcount, int firstrow) throws DaoException;
}
