package com.epam.zt.testing.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinishTestAction implements Action {
    private ActionResult finish = new ActionResult("finishTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        int timeExpire = Integer.parseInt(req.getParameter("timeExpire"));
        if (timeExpire == 1) {
            req.setAttribute("timeExpire", "timeExpire");
        }
        return finish;
    }
}
