package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GroupCategoryPageAction implements Action {
    private ActionResult groupCategory = new ActionResult("groupCategory?page=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        int firstrow = (int) req.getSession().getAttribute("firstrow");
        int rowcount = (int) req.getSession().getAttribute("rowcount");
        if (page.equals("next")) {
            firstrow = firstrow + rowcount;
            List<Group> groups = GroupService.findSublist(rowcount, firstrow);
            if (groups.size() == rowcount) {
                List<Group> checkNext = GroupService.findSublist(1, rowcount + firstrow);

                if (checkNext.size() != 0) {
                    req.getSession().setAttribute("next", "yes");
                } else {
                    req.getSession().setAttribute("next", "no");
                }
            } else {
                req.getSession().setAttribute("next", "no");
            }
            req.getSession().setAttribute("prev", "yes");
            req.getSession().setAttribute("groups", groups);
        }
        if (page.equals("prev")) {
            firstrow = firstrow - rowcount;
            List<Group> groups = GroupService.findSublist(rowcount, firstrow);
            if (firstrow == 0) {
                req.getSession().setAttribute("prev", "no");
            } else {
                req.getSession().setAttribute("prev", "yes");
            }
            req.getSession().setAttribute("next", "yes");
            req.getSession().setAttribute("groups", groups);
        }
        req.getSession().setAttribute("firstrow", firstrow);
        return groupCategory;
    }
}
