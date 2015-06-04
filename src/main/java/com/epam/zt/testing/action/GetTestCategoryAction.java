package com.epam.zt.testing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTestCategoryAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String delete = req.getParameter("delete");
        req.setAttribute("deleteTest", delete);
        return testCategory;
    }
}
