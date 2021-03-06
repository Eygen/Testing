package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetTestCategoryAction implements Action {
    private ActionResult testCategory = new ActionResult("testCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("page") == null) {
            int firstrow = 0;
            int rowcount = 3;
            List<Test> tests = TestService.findSublist(rowcount, firstrow);
            if (tests.size() == 0) {
                req.getSession().setAttribute("exist_tests", "no");
            } else {
                if (tests.size() == rowcount) {
                    List<Test> checkNext = TestService.findSublist(1, rowcount);

                    if (checkNext.size() != 0) {
                        req.getSession().setAttribute("next", "yes");
                    } else {
                        req.getSession().setAttribute("next", "no");
                    }
                } else {
                    req.getSession().setAttribute("next", "no");
                }
                req.getSession().setAttribute("prev", "no");
                req.getSession().setAttribute("exist_tests", "yes");
                req.getSession().setAttribute("firstrow", firstrow);
                req.getSession().setAttribute("rowcount", rowcount);
                req.getSession().setAttribute("tests", tests);
            }
        }
        String delete = req.getParameter("delete");
        req.setAttribute("delete", delete);
        return testCategory;
    }
}
