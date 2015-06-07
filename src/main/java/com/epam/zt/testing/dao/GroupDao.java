package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Group;

import java.util.List;

public interface GroupDao extends Dao<Group> {
    List<Group> findByName(String groupName) throws DaoException;

    List<Group> findSublist(int rowcount, int firstrow) throws DaoException;

    Group findByFullName(String name) throws DaoException;
}
