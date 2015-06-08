package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Answer;
import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetTestEditorAction implements Action {
    private ActionResult testEditor = new ActionResult("testEditor");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        if (req.getParameter("question_id") != null) {
            int id = Integer.parseInt(req.getParameter("question_id"));
            Question question = QuestionService.findById(id);
            List<Answer> answers = question.getAnswers();
            req.setAttribute("firstAnswer", answers.get(0).getDescription());
            req.setAttribute("secondAnswer", answers.get(1).getDescription());
            req.setAttribute("thirdAnswer", answers.get(2).getDescription());
            req.setAttribute("fourthAnswer", answers.get(3).getDescription());
            req.getSession().setAttribute("editQuestion", question);
        } else {
            req.getSession().setAttribute("editQuestion", null);
        }
        req.getSession().setAttribute("test", test);
        return testEditor;
    }
}
