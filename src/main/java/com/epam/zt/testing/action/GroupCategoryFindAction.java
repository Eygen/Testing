package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupCategoryFindAction implements Action {
    private ActionResult groupCategory = new ActionResult("groupCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String groupName = req.getParameter("groupName");
        Group group = GroupService.findByName(groupName);
        if (group == null) {
            req.setAttribute("found", "not_found");
            return groupCategory;
        }
        req.getSession().setAttribute("foundGroup", group);
        req.setAttribute("found", "found");
        return groupCategory;
    }
}
