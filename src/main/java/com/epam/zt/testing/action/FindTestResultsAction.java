package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Mark;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.MarkService;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindTestResultsAction implements Action {
    private ActionResult result = new ActionResult("testResults");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        Student student = StudentService.findByName(lastName, firstName);
        if (student == null) {
            req.setAttribute("studentError", "No such student!");
            return result;
        }
        List<Test> tests = QuestionService.findPassedTests(student, tutor);
        if (tests.size() == 0) {
            req.setAttribute("foundByStudent", "not_found");
            return result;
        }
        for (Test test : tests) {
            Mark mark = MarkService.findMark(student, test);
            test.setMark(mark);
        }
        req.getSession().setAttribute("foundStudent", student);
        req.getSession().setAttribute("studentTests", tests);
        req.setAttribute("foundByStudent", "found");
        return result;
    }

}
