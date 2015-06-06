package com.epam.zt.testing.action;

import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCategoryDeleteAction implements Action {
    ActionResult userCategory = new ActionResult("userCategory?delete=1", true);
    ActionResult back = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("foundUser");
        User currentUser = (User) req.getSession().getAttribute("user");
        int idUser = user.getId();
        int idCurrent = currentUser.getId();
        if (idUser == idCurrent) {
            req.setAttribute("deleteError", 1);
            return back;
        }
        String roleName = user.getRole().getName();
        if (roleName.equals("user")) {
            UserService.deleteUser(user);
        } else {
            UserService.deleteChild(user);
        }
        req.getSession().setAttribute("found", "");
        return userCategory;
    }
}
