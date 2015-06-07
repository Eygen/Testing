package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.GroupDao;
import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class GroupService {

    public static Group findById(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        Group group = groupDao.findById(id);
        if (group != null) {
            List<Student> students = StudentService.findByGroup(group);
            group.setStudents(students);
        }
        groupDao.close();
        return group;
    }

    public static List<Group> findByName(String groupName) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        List<Group> groups = groupDao.findByName(groupName);
        if (groups.size() != 0) {
            for (Group group : groups) {
                List<Student> students = StudentService.findByGroup(group);
                group.setStudents(students);
            }
        }
        groupDao.close();
        return groups;
    }

    public static List<Group> findAll() {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        List<Group> groups = groupDao.findAll();
        groupDao.close();
        return groups;
    }

    public static void createGroup(Group group) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        groupDao.create(group);
        groupDao.close();
    }

    public static void deleteGroup(Group group) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        groupDao.delete(group);
        groupDao.close();
    }

    public static List<Group> findSublist(int rowcount, int firstrow) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        List<Group> groups = groupDao.findSublist(rowcount, firstrow);
        if (groups.size() != 0) {
            for (Group group : groups) {
                List<Student> students = StudentService.findByGroup(group);
                group.setStudents(students);
            }
        }
        groupDao.close();
        return groups;
    }

    public static Group findByFullName(String name) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        GroupDao groupDao = factory.getGroupDao();
        Group group = groupDao.findByFullName(name);
        groupDao.close();
        return group;
    }
}
