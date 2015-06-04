package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestCategoryFindByStudentAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String studentLastname = req.getParameter("studentLastname");
        String studentFirstname = req.getParameter("studentFirstname");
        Student student = StudentService.findByName(studentLastname, studentFirstname);
        if (student == null) {
            req.setAttribute("studentError", "No such student!");
            return testCategory;
        }
        List<Test> tests = TestService.findPassedTests(student);
        if (tests.size() == 0) {
            req.setAttribute("foundByStudent", "not_found");
            return testCategory;
        }
        req.getSession().setAttribute("foundStudent", student);
        req.getSession().setAttribute("studentTests", tests);
        req.setAttribute("foundByStudent", "found");
        return testCategory;
    }
}
