package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetGroupCategoryAction implements Action {
    private ActionResult group = new ActionResult("groupCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") == null) {
            int firstrow = 0;
            int rowcount = 3;
            List<Group> groups = GroupService.findSublist(rowcount, firstrow);
            if (groups.size() == 0) {
                req.getSession().setAttribute("exist_groups", "no");
            } else {
                if (groups.size() == rowcount) {
                    List<Group> checkNext = GroupService.findSublist(1, rowcount);

                    if (checkNext.size() != 0) {
                        req.getSession().setAttribute("next", "yes");
                    } else {
                        req.getSession().setAttribute("next", "no");
                    }
                } else {
                    req.getSession().setAttribute("next", "no");
                }
                req.getSession().setAttribute("prev", "no");
                req.getSession().setAttribute("exist_groups", "yes");
                req.getSession().setAttribute("firstrow", firstrow);
                req.getSession().setAttribute("rowcount", rowcount);
                req.getSession().setAttribute("groups", groups);
            }
        }
        String delete = req.getParameter("delete");
        req.setAttribute("delete", delete);
        String success = req.getParameter("addSuccess");
        req.setAttribute("addSuccess", success);
        return group;
    }
}
