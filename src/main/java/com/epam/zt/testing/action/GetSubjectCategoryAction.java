package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetSubjectCategoryAction implements Action {
    private ActionResult subject = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") == null) {
            int firstrow = 0;
            int rowcount = 3;
            List<Subject> subjects = SubjectService.findSublist(rowcount, firstrow);
            if (subjects.size() == 0) {
                req.getSession().setAttribute("exist_subjects", "no");
            } else {
                if (subjects.size() == rowcount) {
                    List<Subject> checkNext = SubjectService.findSublist(1, rowcount);

                    if (checkNext.size() != 0) {
                        req.getSession().setAttribute("next", "yes");
                    } else {
                        req.getSession().setAttribute("next", "no");
                    }
                } else {
                    req.getSession().setAttribute("next", "no");
                }
                req.getSession().setAttribute("prev", "no");
                req.getSession().setAttribute("exist_subjects", "yes");
                req.getSession().setAttribute("firstrow", firstrow);
                req.getSession().setAttribute("rowcount", rowcount);
                req.getSession().setAttribute("subjects", subjects);
            }
        }
        String delete = req.getParameter("delete");
        req.setAttribute("delete", delete);
        String success = req.getParameter("addSuccess");
        req.setAttribute("addSuccess", success);
        return subject;
    }
}
