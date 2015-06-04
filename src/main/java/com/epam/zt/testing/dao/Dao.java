package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity> {
    List<T> findAll() throws DaoException;

    T findById(Integer id) throws DaoException;

    boolean create(T entity) throws DaoException;

    boolean update(T entity) throws DaoException;

    boolean delete(T entity) throws DaoException;

    void close();
}
