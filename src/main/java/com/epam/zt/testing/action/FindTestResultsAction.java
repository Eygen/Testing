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
import java.util.ArrayList;
import java.util.List;

public class FindTestResultsAction implements Action {
    private ActionResult result = new ActionResult("testResults");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        List<Student> students = new ArrayList<>();
        if (lastName.equals("") && (firstName.equals(""))) {
            req.setAttribute("found", "empty");
            return result;
        } else if ((!lastName.equals("")) && (!firstName.equals(""))) {
            students = StudentService.findByName(lastName, firstName);
        } else if (!lastName.equals("")) {
            students = StudentService.findByLastname(lastName);
        } else if (!firstName.equals("")) {
            students = StudentService.findByFirstname(firstName);
        }
        if (students.size() == 0) {
            req.setAttribute("studentError", 1);
            return result;
        }

        List<Test> tests = new ArrayList<>();
        for (Student student : students) {
            List<Test> foundTests = QuestionService.findPassedTests(student, tutor);
            for (Test foundTest : foundTests) {
                Mark mark = MarkService.findMark(student, foundTest);
                foundTest.setMark(mark);
                tests.add(foundTest);
            }
        }
        if (tests.size() == 0) {
            req.setAttribute("found", "not_found");
            return result;
        }
        req.getSession().setAttribute("studentTests", tests);
        req.setAttribute("found", "found");
        return result;
    }

}
