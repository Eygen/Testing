package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.TutorDao;
import com.epam.zt.testing.model.Tutor;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class TutorService {

    public static boolean createTutor(Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        boolean create = tutorDao.create(tutor);
        tutorDao.close();
        return create;
    }

    public void createTutors(String filename) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        tutorDao.close();
    }

    public static boolean deleteTutor(Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        boolean delete = tutorDao.delete(tutor);
        tutorDao.close();
        return delete;
    }

    public static Tutor findTutor(String login, String password) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        Tutor tutor = tutorDao.findTutor(login, password);
        tutorDao.close();
        return tutor;
    }

    public static boolean updateTutor(Tutor tutor) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        boolean update = tutorDao.update(tutor);
        tutorDao.close();
        return update;
    }

    public static Tutor findByName(String lastname, String firstname) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        Tutor tutor = tutorDao.findByName(lastname, firstname);
        tutorDao.close();
        return tutor;
    }

    public static Tutor findById(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        TutorDao tutorDao = factory.getTutorDao();
        Tutor tutor = tutorDao.findById(id);
        tutorDao.close();
        return tutor;
    }
}
