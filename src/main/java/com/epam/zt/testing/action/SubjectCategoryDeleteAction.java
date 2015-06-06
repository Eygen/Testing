package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.SubjectService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectCategoryDeleteAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory?delete=1", true);
    private ActionResult back = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Subject subject = (Subject) req.getSession().getAttribute("foundSubject");
        List<Test> tests = TestService.findBySubject(subject);
        if (tests.size() > 0) {
            req.setAttribute("deleteError", 1);
            return back;
        }
        SubjectService.deleteSubject(subject);
        req.getSession().setAttribute("found", "");
        return subjectCategory;
    }
}
