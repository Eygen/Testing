package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserCategoryDetailsAction implements Action {
    private ActionResult details = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("person_id"));
        User user = UserService.findById(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        req.setAttribute("menu", 1);
        List<Role> roles = RoleService.findAll();
        req.getSession().setAttribute("roles", roles);
        req.getSession().setAttribute("foundUser", user);
        req.getSession().setAttribute("foundUsers", users);
        req.setAttribute("found", "found");
        return details;
    }
}
