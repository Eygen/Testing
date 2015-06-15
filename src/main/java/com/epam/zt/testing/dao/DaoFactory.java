package com.epam.zt.testing.dao;

import com.epam.zt.testing.dao.Jdbc.JdbcDaoFactory;

public abstract class DaoFactory {

    public abstract UserDao getUserDao();
    public abstract AdminDao getAdminDao();
    public abstract StudentDao getStudentDao();
    public abstract TutorDao getTutorDao();
    public abstract GroupDao getGroupDao();
    public abstract SubjectDao getSubjectDao();
    public abstract TestDao getTestDao();
    public abstract QuestionDao getQuestionDao();
    public abstract MarkDao getMarkDao();
    public abstract RoleDao getRoleDao();

    public static DaoFactory getInstance(Type type) {
        switch (type) {
            case JDBC:
                return JdbcDaoFactory.getInstance();
            default:
                throw new DaoException("Such type does not exist!");
        }
    }

    public enum Type {
        JDBC
    }

}
