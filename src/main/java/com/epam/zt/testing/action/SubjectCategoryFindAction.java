package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectCategoryFindAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter("subjectName");
        List<Subject> subjects = SubjectService.findByName(subjectName);
        if (subjects.size() == 0) {
            req.setAttribute("found", "not_found");
            return subjectCategory;
        }
        if (subjects.size() == 1) {
            req.setAttribute("menu", 1);
            req.getSession().setAttribute("foundSubject", subjects.get(0));
        }
        req.getSession().setAttribute("foundSubjects", subjects);
        req.setAttribute("found", "found");
        return subjectCategory;
    }
}
