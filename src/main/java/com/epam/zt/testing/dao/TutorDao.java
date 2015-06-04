package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Tutor;

public interface TutorDao extends Dao<Tutor> {
    Tutor findTutor(String login, String password) throws DaoException;

    Tutor findByName(String lastname, String firstname) throws DaoException;
}
