package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewMarkAction implements Action {
    private ActionResult marks = new ActionResult("viewMark");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") == null) {
            Student student = (Student) req.getSession().getAttribute("user");
            int firstrow = 0;
            int rowcount = 3;
            List<Mark> marks = MarkService.findSublist(student, rowcount, firstrow);
            if (marks.size() == 0) {
                req.getSession().setAttribute("exist_marks", "no");
            } else {
                if (marks.size() == rowcount) {
                    List<Mark> checkNext = MarkService.findSublist(student, 1, rowcount);

                    if (checkNext.size() != 0) {
                        req.getSession().setAttribute("next", "yes");
                    } else {
                        req.getSession().setAttribute("next", "no");
                    }
                } else {
                    req.getSession().setAttribute("next", "no");
                }
                req.getSession().setAttribute("prev", "no");
                req.getSession().setAttribute("exist_marks", "yes");
                req.getSession().setAttribute("firstrow", firstrow);
                req.getSession().setAttribute("rowcount", rowcount);
                req.getSession().setAttribute("marks", marks);
            }
        }
        return marks;
    }
}
