package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectCategoryDeleteAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Subject subject = (Subject) req.getSession().getAttribute("foundSubject");
        SubjectService.deleteSubject(subject);
        req.setAttribute("delete", "Subject is deleted");
        req.setAttribute("found", "");
        return subjectCategory;
    }
}
