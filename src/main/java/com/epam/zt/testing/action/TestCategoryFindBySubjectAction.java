package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.SubjectService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestCategoryFindBySubjectAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter("subjectName");
        Subject subject = SubjectService.findByFullName(subjectName);
        if (subject == null) {
            req.setAttribute("subjectError", "No such subject!");
            return testCategory;
        }
        List<Test> tests = TestService.findBySubject(subject);
        if (tests.size() == 0) {
            req.setAttribute("foundBySubject", "not_found");
            return testCategory;
        }
        req.getSession().setAttribute("subjectTests", tests);
        req.setAttribute("foundBySubject", "found");
        return testCategory;
    }
}
