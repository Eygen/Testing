package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserCategoryFindAction implements Action {
    ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        User user = UserService.findByName(lastName, firstName);
        if (user == null) {
            req.setAttribute("found", "not_found");
            return userCategory;
        }
        List<Role> roles = RoleService.findAll();
        req.getSession().setAttribute("roles", roles);
        req.getSession().setAttribute("foundUser", user);
        req.setAttribute("found", "found");
        return userCategory;
    }
}
