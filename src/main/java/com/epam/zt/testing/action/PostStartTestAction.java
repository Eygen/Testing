package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Answer;
import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostStartTestAction implements Action {
    private ActionResult start = new ActionResult("passTest", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("passTest");
        List<Question> questions = QuestionService.getQuestions(test);
        int questionNumber = test.getQuestionAmount();
        int num = 0;
        int correct = 0;
        for (Question question : questions) {
            List<Answer> answers = question.getAnswers();
            Collections.shuffle(answers);
        }
        Collections.shuffle(questions);
        List<Question> testQuestions = new ArrayList<>();
        for (int i = 0; i < questionNumber; i++) {
            testQuestions.add(questions.get(i));
        }
        test.setQuestions(testQuestions);
        long timeMillis = System.currentTimeMillis();
        long timeStart = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        long durationTest = 40;
        long durationSeconds = TimeUnit.MINUTES.toSeconds(durationTest);
        long timeFinish = timeStart + durationSeconds;
        req.getSession().setAttribute("num", num);
        req.getSession().setAttribute("correct", correct);
        req.getSession().setAttribute("timeFinish", timeFinish);
        req.getSession().setAttribute("passTest", test);
        return start;
    }
}
