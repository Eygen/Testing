package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.SubjectService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCreateTestAction implements Action {
    private ActionResult testEditor = new ActionResult("testEditor", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Tutor tutor = (Tutor) req.getSession().getAttribute("user");
        String subjectName = req.getParameter("subject");
        String questionAmount = req.getParameter("amountQuestions");
        Subject subject = SubjectService.findByFullName(subjectName);
        Test test = new Test();
        test.setSubject(subject);
        test.setQuestionAmount(Integer.parseInt(questionAmount));
        test.setAuthor(tutor);
        Test createdTest = TestService.createTest(test);
        req.getSession().setAttribute("test", createdTest);
        return testEditor;
    }
}
