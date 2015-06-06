package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserCategoryFindAction implements Action {
    ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = new ArrayList<>();
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        if (lastName.equals("") && (firstName.equals(""))) {
            req.setAttribute("found", "empty");
            return userCategory;
        } else if ((!lastName.equals("")) && (!firstName.equals(""))) {
            users = UserService.findByName(lastName, firstName);
        } else if (!lastName.equals("")) {
            users = UserService.findByLastname(lastName);
        } else if (!firstName.equals("")) {
            users = UserService.findByFirstname(firstName);
        }
        if (users.size() == 0) {
            req.setAttribute("found", "not_found");
            return userCategory;
        } else {
            if (users.size() == 1) {
                req.setAttribute("menu", 1);
                List<Role> roles = RoleService.findAll();
                req.getSession().setAttribute("roles", roles);
                req.getSession().setAttribute("foundUser", users.get(0));
            }
            req.getSession().setAttribute("foundUsers", users);
            req.setAttribute("found", "found");
        }
        return userCategory;
    }
}
