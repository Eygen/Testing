package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTestEditorAction implements Action {
    private ActionResult testEditor = new ActionResult("testEditor");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        req.getSession().setAttribute("test", test);
        return testEditor;
    }
}
