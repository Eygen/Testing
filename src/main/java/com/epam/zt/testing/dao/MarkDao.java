package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;

import java.util.List;
import java.util.UUID;

public interface MarkDao extends Dao<Mark> {
    List<Mark> getMarks(Student student) throws DaoException;

    Mark findByUuid(UUID uuid) throws DaoException;

    Mark findMark(Student student, Test test) throws DaoException;

    List<Mark> findSublist(Student student, int rowcount, int firstrow) throws DaoException;
}
