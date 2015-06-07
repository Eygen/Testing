package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;

import java.util.List;

public interface StudentDao extends Dao<Student> {

    Student findStudent(String login, String password) throws DaoException;

    List<Student> findByGroup(Group group) throws DaoException;

    List<Student> findByName(String lastname, String firstname) throws DaoException;

    void removeGroup(int id) throws DaoException;

    List<Student> findByLastname(String lastName) throws DaoException;

    List<Student> findByFirstname(String firstName) throws DaoException;

    Student findByFullName(String lastName, String firstName) throws DaoException;
}
