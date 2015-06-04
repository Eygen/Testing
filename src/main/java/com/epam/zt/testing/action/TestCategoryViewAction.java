package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TestCategoryViewAction implements Action {
    private ActionResult viewResult = new ActionResult("viewResult", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        Student student = (Student) req.getSession().getAttribute("foundStudent");
        List<Question> questions = QuestionService.findStudentResult(student, test);
        req.getSession().setAttribute("studentResult", questions);
        return viewResult;
    }
}
