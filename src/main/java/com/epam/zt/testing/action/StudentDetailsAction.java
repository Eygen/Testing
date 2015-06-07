package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailsAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        int id = Integer.parseInt(req.getParameter("student_id"));
        Student student = StudentService.findById(id);
        List<Student> students = new ArrayList<>();
        students.add(student);
        boolean check = TestService.checkActive(student, test);
        if (check) {
            req.setAttribute("testAssign", "assign");
        } else {
            req.setAttribute("testAssign", "not_assign");
        }
        req.setAttribute("menu", 1);
        req.getSession().setAttribute("foundStudent", student);
        req.getSession().setAttribute("foundStudents", students);
        req.setAttribute("found", "found");
        return assignTest;
    }
}
