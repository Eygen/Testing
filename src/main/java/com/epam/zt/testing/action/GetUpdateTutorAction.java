package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Tutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateTutorAction implements Action {
    private ActionResult update = new ActionResult("tutorSettings");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        req.setAttribute("firstname", tutor.getFirstName());
        req.setAttribute("lastname", tutor.getLastName());
        req.setAttribute("email", tutor.getEmail());
        return update;
    }
}
