package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUpdateStudentAction implements Action {
    private ActionResult update = new ActionResult("studentSettings");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Student student = (Student) req.getSession().getAttribute("user");
        req.setAttribute("firstname", student.getFirstName());
        req.setAttribute("lastname", student.getLastName());
        req.setAttribute("email", student.getEmail());
        Group group = student.getGroup();
        if (group != null) {
            req.setAttribute("studentGroup", group.getName());
        }
        List<Group> groups = GroupService.findAll();
        req.getSession().setAttribute("groups", groups);
        return update;
    }
}
