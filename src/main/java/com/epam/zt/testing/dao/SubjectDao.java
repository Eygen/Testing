package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {
    List<Subject> findByTutor(Tutor tutor) throws DaoException;

    List<Subject> findByName(String name) throws DaoException;

    Subject findByFullName(String name) throws DaoException;

    List<Subject> findSublist(int rowcount, int firstrow) throws DaoException;
}
