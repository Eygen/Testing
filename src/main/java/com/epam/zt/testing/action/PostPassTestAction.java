package com.epam.zt.testing.action;

import com.epam.zt.testing.model.*;
import com.epam.zt.testing.service.MarkService;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class PostPassTestAction implements Action {
    private ActionResult back = new ActionResult("passTest", true);
    private ActionResult finish = new ActionResult("finishTest?timeExpire=0", true);
    private ActionResult forceFinish = new ActionResult("finishTest?timeExpire=1", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        long timeFinish = (long) req.getSession().getAttribute("timeFinish");
        Student student = (Student) req.getSession().getAttribute("user");
        Test passTest = (Test) req.getSession().getAttribute("passTest");
        Test test = (Test) req.getSession().getAttribute("passTest");
        int correct = (int) req.getSession().getAttribute("correct");
        int num = (int) req.getSession().getAttribute("num");
        num++;
        Question question = (Question) req.getSession().getAttribute("question");
        int answerId = Integer.parseInt(req.getParameter("answer"));
        Answer answer = QuestionService.findAnswer(answerId);
        QuestionService.createStudentResult(student, test, question, answer);
        if (answer.isCorrect()) {
            correct++;
        }
        req.getSession().setAttribute("num", num);
        req.getSession().setAttribute("correct", correct);
        if (num == test.getQuestions().size()) {
            Mark mark = MarkService.createMark(student, passTest, correct);
            TestService.deletePassedTest(student, passTest);
            req.getSession().setAttribute("mark", mark);
            return finish;
        }
        long timeMillis = System.currentTimeMillis();
        long timeCurrent = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        if (timeCurrent > timeFinish) {
            Mark mark = MarkService.createMark(student, passTest, correct);
            TestService.deletePassedTest(student, passTest);
            req.getSession().setAttribute("mark", mark);
            return forceFinish;
        }
        return back;
    }
}
