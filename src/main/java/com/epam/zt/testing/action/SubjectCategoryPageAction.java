package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectCategoryPageAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory?page=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        int firstrow = (int) req.getSession().getAttribute("firstrow");
        int rowcount = (int) req.getSession().getAttribute("rowcount");
        if (page.equals("next")) {
            firstrow = firstrow + rowcount;
            List<Subject> subjects = SubjectService.findSublist(rowcount, firstrow);
            if (subjects.size() == rowcount) {
                List<Subject> checkNext = SubjectService.findSublist(1, rowcount + firstrow);

                if (checkNext.size() != 0) {
                    req.getSession().setAttribute("next", "yes");
                } else {
                    req.getSession().setAttribute("next", "no");
                }
            } else {
                req.getSession().setAttribute("next", "no");
            }
            req.getSession().setAttribute("prev", "yes");
            req.getSession().setAttribute("subjects", subjects);
        }
        if (page.equals("prev")) {
            firstrow = firstrow - rowcount;
            List<Subject> subjects = SubjectService.findSublist(rowcount, firstrow);
            if (firstrow == 0) {
                req.getSession().setAttribute("prev", "no");
            } else {
                req.getSession().setAttribute("prev", "yes");
            }
            req.getSession().setAttribute("next", "yes");
            req.getSession().setAttribute("subjects", subjects);
        }
        req.getSession().setAttribute("firstrow", firstrow);
        return subjectCategory;
    }
}
