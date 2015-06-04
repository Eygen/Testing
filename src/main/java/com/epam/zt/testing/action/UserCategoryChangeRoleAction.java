package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCategoryChangeRoleAction implements Action {
    private ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("foundUser");
        String newRole = req.getParameter("newRole");
        Role role = RoleService.findByName(newRole);
        user.setRole(role);
        UserService.changeRole(user);
        req.setAttribute("found", "");
        req.setAttribute("roleChange", "User's role is changed");
        return userCategory;
    }
}
