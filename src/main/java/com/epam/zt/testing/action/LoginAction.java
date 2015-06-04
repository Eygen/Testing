package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Admin;
import com.epam.zt.testing.model.Role;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.AdminService;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TutorService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    private ActionResult studentHome = new ActionResult("studentHome", true);
    private ActionResult tutorHome = new ActionResult("tutorHome", true);
    private ActionResult adminHome = new ActionResult("adminHome", true);
    private ActionResult loginAgain = new ActionResult("login");
    private ActionResult registrySuccess = new ActionResult("registrySuccess", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = UserService.findUser(login, password);
        if (role != null) {
            switch (role.getName()) {
                case "user":
                    return registrySuccess;
                case "admin":
                    Admin admin = AdminService.findAdmin(login, password);
                    req.getSession().setAttribute("user", admin);
                    return adminHome;
                case "tutor":
                    Tutor tutor = TutorService.findTutor(login, password);
                    req.getSession().setAttribute("user", tutor);
                    return tutorHome;
                case "student":
                    Student student = StudentService.findStudent(login, password);
                    req.getSession().setAttribute("user", student);
                    return studentHome;
            }
        } else {
            req.setAttribute("loginError", "Login or password incorrect");
            return loginAgain;
        }
        return loginAgain;
    }
}
