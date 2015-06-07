package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MarkPageAction implements Action {
    private ActionResult marks = new ActionResult("viewMark?page=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Student student = (Student) req.getSession().getAttribute("user");
        String page = req.getParameter("page");
        int firstrow = (int) req.getSession().getAttribute("firstrow");
        int rowcount = (int) req.getSession().getAttribute("rowcount");
        if (page.equals("next")) {
            firstrow = firstrow + rowcount;
            List<Mark> marks = MarkService.findSublist(student, rowcount, firstrow);
            if (marks.size() == rowcount) {
                List<Mark> checkNext = MarkService.findSublist(student, 1, rowcount + firstrow);

                if (checkNext.size() != 0) {
                    req.getSession().setAttribute("next", "yes");
                } else {
                    req.getSession().setAttribute("next", "no");
                }
            } else {
                req.getSession().setAttribute("next", "no");
            }
            req.getSession().setAttribute("prev", "yes");
            req.getSession().setAttribute("marks", marks);
        }
        if (page.equals("prev")) {
            firstrow = firstrow - rowcount;
            List<Mark> marks = MarkService.findSublist(student, rowcount, firstrow);
            if (firstrow == 0) {
                req.getSession().setAttribute("prev", "no");
            } else {
                req.getSession().setAttribute("prev", "yes");
            }
            req.getSession().setAttribute("next", "yes");
            req.getSession().setAttribute("marks", marks);
        }
        req.getSession().setAttribute("firstrow", firstrow);
        return marks;
    }
}
