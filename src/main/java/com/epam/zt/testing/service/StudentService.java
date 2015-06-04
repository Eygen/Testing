package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.StudentDao;
import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class StudentService {

    public static boolean createStudent(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        boolean create = studentDao.create(student);
        studentDao.close();
        return create;
    }

    public static boolean deleteStudent(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        boolean delete = studentDao.delete(student);
        studentDao.close();
        return delete;
    }

    public static Student findStudent(String login, String password) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        Student student = studentDao.findStudent(login, password);
        studentDao.close();
        return student;
    }

    public static boolean updateStudent(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        boolean update = studentDao.update(student);
        studentDao.close();
        return update;
    }

    public static List<Student> findByGroup(Group group) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        List<Student> students = studentDao.findByGroup(group);
        studentDao.close();
        return students;
    }

    public static Student findByName(String lastname, String firstname) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        Student student = studentDao.findByName(lastname, firstname);
        studentDao.close();
        return student;
    }
}
