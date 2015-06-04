package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPassTestAction implements Action {
    private ActionResult pass = new ActionResult("passTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("passTest");
        int num = (int) req.getSession().getAttribute("num");
        Question question = test.getQuestions().get(num);
        req.getSession().setAttribute("question", question);
        return pass;
    }
}
