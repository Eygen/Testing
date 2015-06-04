package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetHomeStudentAction implements Action {
    private ActionResult home = new ActionResult("studentHome");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Student student = (Student) req.getSession().getAttribute("user");
        if (student.getGroup() == null) {
            req.setAttribute("emptyGroup", "empty");
        } else {
            List<Test> tests = TestService.findByStudent(student);
            if (tests.size() == 0) {
                req.setAttribute("testList", "empty");
            } else if (tests.size() > 0) {
                req.setAttribute("testList", "exist");
                req.setAttribute("tests", tests);
            }
        }
        return home;
    }
}
