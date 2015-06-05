package com.epam.zt.testing.action;

import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUserCategoryAction implements Action {
    private ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") == null) {
            int firstrow = 0;
            int rowcount = 3;
            List<User> users = UserService.findSublist(rowcount, firstrow);
            if (users.size() == 0) {
                req.getSession().setAttribute("exist_users", "no");
            } else {
                if (users.size() == rowcount) {
                    List<User> checkNext = UserService.findSublist(1, rowcount);

                    if (checkNext.size() != 0) {
                        req.setAttribute("next", "yes");
                    } else {
                        req.setAttribute("next", "no");
                    }
                } else {
                    req.setAttribute("next", "no");
                }
                req.setAttribute("prev", "no");
                req.getSession().setAttribute("exist_users", "yes");
                req.getSession().setAttribute("firstrow", firstrow);
                req.getSession().setAttribute("rowcount", rowcount);
                req.getSession().setAttribute("users", users);
            }
        }
        return userCategory;
    }
}
