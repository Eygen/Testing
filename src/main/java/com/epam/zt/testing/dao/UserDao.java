package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    Role findUser(String login, String password) throws DaoException;

    boolean existLogin(String login) throws DaoException;

    void close();

    List<User> findByName(String lastName, String firstName) throws DaoException;

    void deleteChild(User user) throws DaoException;

    List<User> findSublist(int rowcount, int firstrow) throws DaoException;

    List<User> findByLastname(String lastName) throws DaoException;

    List<User> findByFirstname(String firstName) throws DaoException;
}
