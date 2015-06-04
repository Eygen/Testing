package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCategoryDeleteAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        TestService.deleteTest(test);
        req.setAttribute("delete", "Test is deleted");
        /*req.setAttribute("foundBySubject", "");
        req.setAttribute("foundByStudent", "");*/
        return testCategory;
    }
}
