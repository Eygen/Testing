package com.epam.zt.testing.action;

import com.epam.zt.testing.model.User;
import com.epam.zt.testing.service.RoleService;
import com.epam.zt.testing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.epam.zt.testing.action.ValidationUtil.validate;

public class RegistryAction implements Action {

    private ActionResult registryAgain = new ActionResult("registry");
    private ActionResult registrySuccess = new ActionResult("registrySuccess", true);
    private static final String loginPattern = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,15}$";
    private static final String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int i = 0;
        String login = req.getParameter("login");
        if (!validate(loginPattern, login)) {
            req.setAttribute("loginError", "Login is not correct"); /*"Логин должен быть не менее 4 символов (цифры и буквы)"*/
            login = "";
            i++;
        } else if (UserService.existLogin(login)) {
            req.setAttribute("loginError", "Such login exist");
            login = "";
            i++;
        }
        String password = req.getParameter("password");
        if (!validate(passwordPattern, password)) {
            req.setAttribute("passwordError", "Password is not correct"); /*"Пароль должен быть не менее 8 символов. Допускаются буквы, цифры и спецсимволы"*/
            i++;
        }
        String passwordConfirm = req.getParameter("password_confirmation");
        if (!password.equals(passwordConfirm)) {
            req.setAttribute("confirmError", "Password is not compare");
            i++;
        }
        if (i > 0) {
            req.setAttribute("first_name", req.getParameter("first_name"));
            req.setAttribute("last_name", req.getParameter("last_name"));
            req.setAttribute("login", login);
            req.setAttribute("email", req.getParameter("email"));
            return registryAgain;
        }

        User user = new User();
        user.setFirstName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setEmail(req.getParameter("email"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setRegisterDate(new Date());
        user.setRole(RoleService.findByName("user"));
        UserService.createUser(user);
        return registrySuccess;
    }


}
