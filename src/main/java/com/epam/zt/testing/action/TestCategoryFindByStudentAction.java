package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class TestCategoryFindByStudentAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> students = new ArrayList<>();
        String lastName = req.getParameter("studentLastname");
        String firstName = req.getParameter("studentFirstname");
        if (lastName.equals("") && (firstName.equals(""))) {
            req.setAttribute("foundByStudent", "empty");
            return testCategory;
        } else if ((!lastName.equals("")) && (!firstName.equals(""))) {
            students = StudentService.findByName(lastName, firstName);
        } else if (!lastName.equals("")) {
            students = StudentService.findByLastname(lastName);
        } else if (!firstName.equals("")) {
            students = StudentService.findByFirstname(firstName);
        }
        if (students.size() == 0) {
            req.setAttribute("studentError", 1);
            return testCategory;
        }
        List<Test> tests = new ArrayList<>();
        for (Student student : students) {
            List<Test> foundTests = TestService.findPassedTests(student);
            for (Test foundTest : foundTests) {
                foundTest.setStudent(student);
                tests.add(foundTest);
            }
        }
        if (tests.size() == 0) {
            req.setAttribute("foundByStudent", "not_found");
            return testCategory;
        }
        req.getSession().setAttribute("studentTests", tests);
        req.setAttribute("foundByStudent", "found");
        return testCategory;
    }
}
