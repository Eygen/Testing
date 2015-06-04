package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAssignTestAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        List<Group> groups = GroupService.findAll();
        req.getSession().setAttribute("groups", groups);
        String success = req.getParameter("success");
        req.setAttribute("success", success);
        return assignTest;
    }
}
