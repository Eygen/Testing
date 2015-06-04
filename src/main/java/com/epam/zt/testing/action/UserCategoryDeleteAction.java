package com.epam.zt.testing.action;

import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCategoryDeleteAction implements Action {
    ActionResult userCategory = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("foundUser");
        String roleName = user.getRole().getName();
        if (roleName.equals("user")) {
            UserService.deleteUser(user);
        } else {
            UserService.deleteChild(user);
        }
        req.setAttribute("delete", "User is deleted");
        req.setAttribute("found", "");
        return userCategory;
    }
}
