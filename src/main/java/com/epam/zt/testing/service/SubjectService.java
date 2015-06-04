package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.SubjectDao;
import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class SubjectService {

    public static boolean createSubject(Subject subject) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        SubjectDao subjectDao = factory.getSubjectDao();
        boolean create = subjectDao.create(subject);
        subjectDao.close();
        return create;
    }

    public static List<Subject> findByTutor(Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        SubjectDao subjectDao = factory.getSubjectDao();
        List<Subject> subjects = subjectDao.findByTutor(tutor);
        subjectDao.close();
        return subjects;
    }

    public static Subject findByName(String name) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        SubjectDao subjectDao = factory.getSubjectDao();
        Subject subject = subjectDao.findByName(name);
        subjectDao.close();
        return subject;
    }

    public static void deleteSubject(Subject subject) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        SubjectDao subjectDao = factory.getSubjectDao();
        subjectDao.delete(subject);
        subjectDao.close();
    }

    public static Subject findById(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        SubjectDao subjectDao = factory.getSubjectDao();
        Subject subject = subjectDao.findById(id);
        subjectDao.close();
        return subject;
    }
}
