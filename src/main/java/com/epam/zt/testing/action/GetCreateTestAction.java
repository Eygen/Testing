package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCreateTestAction implements Action {
    private ActionResult create = new ActionResult("createTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        List<Subject> subjects = SubjectService.findByTutor(tutor);
        req.getSession().setAttribute("subjects", subjects);
        return create;
    }
}
