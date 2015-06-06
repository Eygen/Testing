package com.epam.zt.testing.action;

import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserCategoryPageAction implements Action {
    private ActionResult userCategory = new ActionResult("userCategory?page=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        int firstrow = (int) req.getSession().getAttribute("firstrow");
        int rowcount = (int) req.getSession().getAttribute("rowcount");
        if (page.equals("next")) {
            firstrow = firstrow + rowcount;
            List<User> users = UserService.findSublist(rowcount, firstrow);
            if (users.size() == rowcount) {
                List<User> checkNext = UserService.findSublist(1, rowcount + firstrow);

                if (checkNext.size() != 0) {
                    req.getSession().setAttribute("next", "yes");
                } else {
                    req.getSession().setAttribute("next", "no");
                }
            } else {
                req.getSession().setAttribute("next", "no");
            }
            req.getSession().setAttribute("prev", "yes");
            req.getSession().setAttribute("users", users);
        }
        if (page.equals("prev")) {
            firstrow = firstrow - rowcount;
            List<User> users = UserService.findSublist(rowcount, firstrow);
            if (firstrow == 0) {
                req.getSession().setAttribute("prev", "no");
            } else {
                req.getSession().setAttribute("prev", "yes");
            }
            req.getSession().setAttribute("next", "yes");
            req.getSession().setAttribute("users", users);
        }
        req.getSession().setAttribute("firstrow", firstrow);
        return userCategory;
    }
}
