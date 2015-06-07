package com.epam.zt.testing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTestResultsAction implements Action {
    private ActionResult testResults = new ActionResult("testResults");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return testResults;
    }
}
