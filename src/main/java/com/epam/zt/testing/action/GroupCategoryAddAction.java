package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupCategoryAddAction implements Action {
    private ActionResult groupCategory = new ActionResult("groupCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String groupName = req.getParameter("newGroup");
        Group group = new Group();
        group.setName(groupName);
        GroupService.createGroup(group);
        req.setAttribute("addSuccess", "Group successfully created!");
        return groupCategory;
    }
}
