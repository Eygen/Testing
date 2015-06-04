package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostEditTestTutorAction implements Action {
    private ActionResult home = new ActionResult("tutorHome?delete=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        TestService.deleteTest(test);
        return home;
    }
}
