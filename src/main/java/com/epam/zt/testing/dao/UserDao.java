package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;

public interface UserDao extends Dao<User> {
    Role findUser(String login, String password) throws DaoException;

    boolean existLogin(String login) throws DaoException;

    void close();

    User findByName(String lastName, String firstName) throws DaoException;

    void deleteChild(User user) throws DaoException;
}
