package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.UserDao;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class UserService {

    public static Role findUser(String login, String password) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        Role role = userDao.findUser(login, password);
        userDao.close();
        return role;
    }

    public static boolean existLogin(String login) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        boolean exist = userDao.existLogin(login);
        userDao.close();
        return exist;
    }

    public static boolean createUser(User user) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        boolean create = userDao.create(user);
        userDao.close();
        return create;
    }

    public static void changeRole(User user) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        userDao.update(user);
        userDao.close();
    }

    public static void deleteUser(User user) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        userDao.delete(user);
        userDao.close();
    }

    public static User findByName(String lastName, String firstName) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        User user = userDao.findByName(lastName, firstName);
        userDao.close();
        return user;
    }

    public static void deleteChild(User user) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        userDao.deleteChild(user);
        userDao.close();
    }

    public static List<User> findAll() {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        UserDao userDao = factory.getUserDao();
        List<User> users = userDao.findAll();
        userDao.close();
        return users;
    }
}
