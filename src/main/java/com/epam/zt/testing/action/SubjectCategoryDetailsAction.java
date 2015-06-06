package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SubjectCategoryDetailsAction implements Action {
    private ActionResult details = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("subject_id"));
        Subject subject = SubjectService.findById(id);
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        req.setAttribute("menu", 1);
        req.getSession().setAttribute("foundSubject", subject);
        req.getSession().setAttribute("foundSubjects", subjects);
        req.setAttribute("found", "found");
        return details;
    }
}
