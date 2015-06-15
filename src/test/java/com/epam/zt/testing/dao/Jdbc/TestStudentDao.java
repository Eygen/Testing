package com.epam.zt.testing.dao.Jdbc;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.StudentDao;
import com.epam.zt.testing.model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class TestStudentDao {

    @Test
    public void testFindAll() {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        StudentDao studentDao = factory.getStudentDao();
        List<Student> students = studentDao.findAll();
        Assert.assertNotNull(students);
    }
}
