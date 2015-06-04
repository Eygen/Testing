package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetStudentTestAdminAction implements Action {
    private ActionResult studentTest = new ActionResult("viewStudentTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("test_id");
        Test test = TestService.findById(Integer.parseInt(id));
        Student student = (Student) req.getSession().getAttribute("foundStudent");
        List<Question> questions = QuestionService.findStudentResult(student, test);
        req.getSession().setAttribute("questions", questions);
        return studentTest;
    }
}
