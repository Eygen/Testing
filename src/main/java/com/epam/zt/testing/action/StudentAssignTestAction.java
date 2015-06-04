package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentAssignTestAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest?success=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        Student student = (Student) req.getSession().getAttribute("foundStudent");
        TestService.assignTest(student, test);
        return assignTest;
    }
}
