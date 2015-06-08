package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetEditTestTutorAction implements Action {
    private ActionResult edit = new ActionResult("editTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test;
        if (req.getParameter("test_id") == null) {
            test = (Test) req.getSession().getAttribute("test");
        } else {
            String id = req.getParameter("test_id");
            test = TestService.findById(Integer.parseInt(id));
        }
        List<Question> questions = QuestionService.getQuestions(test);
        if (questions.size() == 0) {
            req.setAttribute("emptyQuestions", "empty");
        } else {
            test.setQuestions(questions);
        }
        req.getSession().setAttribute("test", test);
        return edit;
    }
}
