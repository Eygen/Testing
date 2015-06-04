package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.*;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {
    private static JdbcDaoFactory instance = new JdbcDaoFactory();
    private ConnectionPool pool;

    private JdbcDaoFactory() {
        pool = ConnectionPool.getInstance();
    }

    public static JdbcDaoFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        Connection connection = pool.getConnection();
        return new JdbcUserDao(connection);
    }

    @Override
    public AdminDao getAdminDao() {
        Connection connection = pool.getConnection();
        return new JdbcAdminDao(connection);
    }

    @Override
    public StudentDao getStudentDao() {
        Connection connection = pool.getConnection();
        return new JdbcStudentDao(connection);
    }

    @Override
    public TutorDao getTutorDao() {
        Connection connection = pool.getConnection();
        return new JdbcTutorDao(connection);
    }

    @Override
    public GroupDao getGroupDao() {
        Connection connection = pool.getConnection();
        return new JdbcGroupDao(connection);
    }

    @Override
    public SubjectDao getSubjectDao() {
        Connection connection = pool.getConnection();
        return new JdbcSubjectDao(connection);
    }

    @Override
    public TestDao getTestDao() {
        Connection connection = pool.getConnection();
        return new JdbcTestDao(connection);
    }

    @Override
    public QuestionDao getQuestionDao() {
        Connection connection = pool.getConnection();
        return new JdbcQuestionDao(connection);
    }

    @Override
    public MarkDao getMarkDao() {
        Connection connection = pool.getConnection();
        return new JdbcMarkDao(connection);
    }

    @Override
    public RoleDao getRoleDao() {
        Connection connection = pool.getConnection();
        return new JdbcRoleDao(connection);
    }

    public void close(Connection connection) {
        pool.releaseConnection(connection);
    }
}
