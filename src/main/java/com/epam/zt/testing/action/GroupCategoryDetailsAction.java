package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GroupCategoryDetailsAction implements Action {
    private ActionResult details = new ActionResult("groupCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("group_id"));
        Group group = GroupService.findById(id);
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        req.setAttribute("menu", 1);
        req.getSession().setAttribute("foundGroup", group);
        req.getSession().setAttribute("foundGroups", groups);
        req.setAttribute("found", "found");
        return details;
    }
}
