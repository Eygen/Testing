package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Admin;
import com.epam.zt.testing.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.zt.testing.action.ValidationUtil.validate;

public class PostUpdateAdminAction implements Action {
    private ActionResult settings = new ActionResult("adminSettings");

    private static final String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Admin admin = (Admin) req.getSession().getAttribute("user");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.getSession().setAttribute("successUpdate","");
        int i = 0;

        if (!admin.getFirstName().equals(firstname)) {
            admin.setFirstName(firstname);
            i++;
        }
        if (!admin.getLastName().equals(lastname)) {
            admin.setLastName(lastname);
            i++;
        }
        if (!admin.getEmail().equals(email)) {
            admin.setEmail(email);
            i++;
        }
        if (!password.equals("")) {
            if (validate(passwordPattern, password)) {
                admin.setPassword(password);
                i++;
            } else {
                i = 0;
                req.setAttribute("passwordError", "Password is not correct");
            }
        }

        if (i > 0) {
            AdminService.updateAdmin(admin);
            req.getSession().setAttribute("user", admin);
            req.setAttribute("successUpdate", "User profile is successfully updated");
        }
        Admin update = (Admin) req.getSession().getAttribute("user");
        req.setAttribute("firstname", update.getFirstName());
        req.setAttribute("lastname", update.getLastName());
        req.setAttribute("email", update.getEmail());
        return settings;
    }
}
