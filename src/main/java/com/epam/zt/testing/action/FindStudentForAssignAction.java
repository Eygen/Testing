package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FindStudentForAssignAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        List<Student> students = new ArrayList<>();
        if (lastName.equals("") && (firstName.equals(""))) {
            req.setAttribute("found", "empty");
            return assignTest;
        } else if ((!lastName.equals("")) && (!firstName.equals(""))) {
            students = StudentService.findByName(lastName, firstName);
        } else if (!lastName.equals("")) {
            students = StudentService.findByLastname(lastName);
        } else if (!firstName.equals("")) {
            students = StudentService.findByFirstname(firstName);
        }
        if (students.size() == 0) {
            req.setAttribute("found", "no_user");
            return assignTest;
        }
        if (students.size() == 1) {
            Student student = students.get(0);
            req.setAttribute("menu", 1);
            req.getSession().setAttribute("foundStudent", student);
            boolean check = TestService.checkActive(student, test);
            if (check) {
                req.setAttribute("testAssign", "assign");
            } else {
                req.setAttribute("testAssign", "not_assign");
            }
        }
        req.getSession().setAttribute("foundStudents", students);
        req.setAttribute("found", "found");
        return assignTest;
    }
}
