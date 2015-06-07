package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GroupCategoryFindAction implements Action {
    private ActionResult groupCategory = new ActionResult("groupCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String groupName = req.getParameter("groupName");
        List<Group> groups = GroupService.findByName(groupName);
        if (groups.size() == 0) {
            req.setAttribute("found", "not_found");
            return groupCategory;
        }
        if (groups.size() == 1) {
            Group group = groups.get(0);
            req.setAttribute("menu", 1);
            req.getSession().setAttribute("foundGroup", group);
            if (group.getStudents().size() > 0) {
                req.setAttribute("noDelete", 1);
            }
        }
        req.getSession().setAttribute("foundGroups", groups);
        req.setAttribute("found", "found");
        return groupCategory;
    }
}
