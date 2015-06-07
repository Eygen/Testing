package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.service.GroupService;
import com.epam.zt.testing.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.zt.testing.action.ValidationUtil.validate;

public class PostUpdateStudentAction implements Action {
    private ActionResult settings = new ActionResult("studentSettings");
    private static final String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Student student = (Student) req.getSession().getAttribute("user");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String group = req.getParameter("group");
        req.getSession().setAttribute("successUpdate","");
        int i = 0;

        if (!student.getFirstName().equals(firstname)) {
            student.setFirstName(firstname);
            i++;
        }
        if (!student.getLastName().equals(lastname)) {
            student.setLastName(lastname);
            i++;
        }
        if (!student.getEmail().equals(email)) {
            student.setEmail(email);
            i++;
        }
        if (!password.equals("")) {
            if (validate(passwordPattern, password)) {
                student.setPassword(password);
                i++;
            } else {
                i = 0;
                req.setAttribute("passwordError", "Password is not correct");
            }
        }
        Group oldGroup = student.getGroup();
        if (group.equals("")) {
            i = 0;
            req.setAttribute("groupError", "Group must be specified!");
        } else if (oldGroup == null || !oldGroup.getName().equals(group)) {
            Group newGroup = GroupService.findByFullName(group);
            student.setGroup(newGroup);
            i++;
        }

        if (i > 0) {
            StudentService.updateStudent(student);
            req.getSession().setAttribute("user", student);
            req.setAttribute("successUpdate", "User profile is successfully updated");
        }
        Student update = (Student) req.getSession().getAttribute("user");
        req.setAttribute("firstname", update.getFirstName());
        req.setAttribute("lastname", update.getLastName());
        req.setAttribute("email", update.getEmail());
        req.setAttribute("studentGroup", update.getGroup().getName());
        return settings;
    }
}
