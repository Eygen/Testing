package com.epam.zt.testing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSubjectCategoryAddAction implements Action {
    private ActionResult subject = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String success = req.getParameter("addSuccess");
        req.setAttribute("addSuccess", success);
        return subject;
    }
}
