package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectCategoryFindAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter("subjectName");
        Subject subject = SubjectService.findByName(subjectName);
        if (subject == null) {
            req.setAttribute("found", "not_found");
            return subjectCategory;
        }
        req.getSession().setAttribute("foundSubject", subject);
        req.setAttribute("found", "found");
        return subjectCategory;
    }
}
