package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.TutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.zt.testing.action.ValidationUtil.validate;

public class PostUpdateTutorAction implements Action {
    private ActionResult settings = new ActionResult("tutorSettings");
    private static final String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.getSession().setAttribute("successUpdate","");
        int i = 0;

        if (!tutor.getFirstName().equals(firstname)) {
            tutor.setFirstName(firstname);
            i++;
        }
        if (!tutor.getLastName().equals(lastname)) {
            tutor.setLastName(lastname);
            i++;
        }
        if (!tutor.getEmail().equals(email)) {
            tutor.setEmail(email);
            i++;
        }
        if (!password.equals("")) {
            if (validate(passwordPattern, password)) {
                tutor.setPassword(password);
                i++;
            } else {
                i = 0;
                req.setAttribute("passwordError", "Password is not correct");
            }
        }

        if (i > 0) {
            TutorService.updateTutor(tutor);
            req.getSession().setAttribute("user", tutor);
            req.setAttribute("successUpdate", "User profile is successfully updated");
        }
        Tutor update = (Tutor) req.getSession().getAttribute("user");
        req.setAttribute("firstname", update.getFirstName());
        req.setAttribute("lastname", update.getLastName());
        req.setAttribute("email", update.getEmail());
        return settings;
    }
}
