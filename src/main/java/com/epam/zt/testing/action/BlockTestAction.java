package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockTestAction implements Action {
    private ActionResult block = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        Student student = (Student) req.getSession().getAttribute("foundStudent");
        TestService.blockTest(student, test);
        req.setAttribute("block", "Test is blocked!");
        return block;
    }
}
