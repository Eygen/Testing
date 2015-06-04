package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateAdminAction implements Action {
    private ActionResult update = new ActionResult("adminSettings");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Admin admin = (Admin) req.getSession().getAttribute("user");
        req.setAttribute("firstname", admin.getFirstName());
        req.setAttribute("lastname", admin.getLastName());
        req.setAttribute("email", admin.getEmail());
        return update;
    }
}
