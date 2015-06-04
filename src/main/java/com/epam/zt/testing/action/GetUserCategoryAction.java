package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUserCategoryAction implements Action {
    private ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Role> roles = RoleService.findAll();
        List<User> users = UserService.findAll();
        if (users.size() == 0) {
            req.setAttribute("exist_users", "no");
        } else {
            req.setAttribute("exist_users", "yes");
            List<User> subList = users.subList(0, 9);
            req.setAttribute("subList", subList);
            req.getSession().setAttribute("users", users);
        }
        req.getSession().setAttribute("roles", roles);
        return userCategory;
    }
}
