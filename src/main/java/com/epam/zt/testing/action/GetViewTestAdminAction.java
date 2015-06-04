package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetViewTestAdminAction implements Action {
    private ActionResult view = new ActionResult("viewTest");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("test_id");
        Test test = TestService.findById(Integer.parseInt(id));
        List<Question> questions = QuestionService.getQuestions(test);
        if (questions.size() == 0) {
            req.setAttribute("emptyQuestions", "empty");
        } else {
            test.setQuestions(questions);
        }
        req.getSession().setAttribute("test", test);
        return view;
    }
}
