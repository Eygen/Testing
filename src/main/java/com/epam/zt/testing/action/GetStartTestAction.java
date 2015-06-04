package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetStartTestAction implements Action {
    private ActionResult start = new ActionResult("startTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("test_id");
        Test test = TestService.findById(Integer.parseInt(id));
        req.getSession().setAttribute("passTest", test);
        return start;
    }
}
