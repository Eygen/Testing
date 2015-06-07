package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupCategoryDeleteAction implements Action {
    private ActionResult groupCategory = new ActionResult("groupCategory?delete=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Group group = (Group) req.getSession().getAttribute("foundGroup");
        GroupService.deleteGroup(group);
        req.setAttribute("delete", "Group is deleted");
        req.setAttribute("found", "");
        return groupCategory;
    }
}
