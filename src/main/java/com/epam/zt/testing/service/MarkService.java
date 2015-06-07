package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.MarkDao;
import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class MarkService {

    public List<Mark> getMarks(Student student) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        MarkDao markDao = factory.getMarkDao();
        List<Mark> marks = markDao.getMarks(student);
        markDao.close();
        return marks;
    }

    public static Mark createMark(Student student, Test test, int correct) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        MarkDao markDao = factory.getMarkDao();
        Mark mark = new Mark();
        mark.setStudent(student);
        mark.setTest(test);
        int value = evaluate(test, correct);
        mark.setValue(value);
        markDao.create(mark);
        return markDao.findByUuid(mark.getUuid());
    }

    private static int evaluate(Test test, int correct) {
        int value;
        int numberQuestions = test.getQuestionAmount();
        int percent = (correct * 100) / numberQuestions;
        if (percent > 89) {
           value = 5;
        } else if (percent < 90 && percent > 74) {
            value = 4;
        } else if (percent < 75 && percent > 49) {
            value = 3;
        } else if (percent < 50 && percent > 9) {
            value = 2;
        } else {
            value = 1;
        }
        return value;
    }

    public static Mark findMark(Student student, Test test) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        MarkDao markDao = factory.getMarkDao();
        Mark mark = markDao.findMark(student, test);
        markDao.close();
        return mark;
    }

    public static List<Mark> findSublist(Student student, int rowcount, int firstrow) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        MarkDao markDao = factory.getMarkDao();
        List<Mark> marks = markDao.findSublist(student, rowcount, firstrow);
        markDao.close();
        return marks;
    }
}
