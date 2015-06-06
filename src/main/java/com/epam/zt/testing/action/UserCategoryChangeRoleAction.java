package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCategoryChangeRoleAction implements Action {
    private ActionResult userCategory = new ActionResult("userCategory?roleChange=1", true);
    private ActionResult back = new ActionResult("userCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("foundUser");
        User currentUser = (User) req.getSession().getAttribute("user");
        int idUser = user.getId();
        int idCurrent = currentUser.getId();
        if (idUser == idCurrent) {
            req.setAttribute("roleError", 1);
            return back;
        }
        String newRole = req.getParameter("newRole");
        Role role = RoleService.findByName(newRole);
        user.setRole(role);
        UserService.changeRole(user);
        req.setAttribute("found", "");
        return userCategory;
    }
}
