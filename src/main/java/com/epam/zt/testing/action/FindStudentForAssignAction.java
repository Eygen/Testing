package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindStudentForAssignAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        Student student = StudentService.findByName(lastName, firstName);
        if (student == null) {
            req.setAttribute("found", "no_user");
            return assignTest;
        }
        boolean check = TestService.checkActive(student, test);
        if (check) {
            req.setAttribute("testAssign", "assign");
        } else {
            req.setAttribute("testAssign", "not_assign");
        }
        req.getSession().setAttribute("foundStudent", student);
        req.setAttribute("found", "found");
        return assignTest;
    }
}
