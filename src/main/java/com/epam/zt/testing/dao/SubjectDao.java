package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {
    List<Subject> findByTutor(Tutor tutor) throws DaoException;

    Subject findByName(String name) throws DaoException;
}
