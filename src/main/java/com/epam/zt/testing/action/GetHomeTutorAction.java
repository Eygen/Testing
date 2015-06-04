package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetHomeTutorAction implements Action {
    private ActionResult home = new ActionResult("tutorHome");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        List<Test> tests = TestService.findByAuthor(tutor);
        if (tests.size() == 0) {
            req.setAttribute("testList", "empty");
        } else if (tests.size() > 0) {
            req.setAttribute("testList", "exist");
            req.setAttribute("tests", tests);
        }
        String delete = req.getParameter("delete");
        req.setAttribute("delete", delete);
        return home;
    }
}
