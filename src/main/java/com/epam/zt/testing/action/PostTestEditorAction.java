package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Answer;
import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class PostTestEditorAction implements Action {
    private ActionResult editor = new ActionResult("testEditor", true);
    //private ActionResult success = new ActionResult("createTest", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        Question question = new Question();
        List<Answer> answers = new ArrayList<>();
        String questionText = req.getParameter("question");
        question.setDescription(questionText);
        question.setTest(test);
        String firstAnswerText = req.getParameter("firstAnswer");
        String secondAnswerText = req.getParameter("secondAnswer");
        String thirdAnswerText = req.getParameter("thirdAnswer");
        String fourthAnswerText = req.getParameter("fourthAnswer");
        addToList(answers, test, firstAnswerText, true);
        addToList(answers, test, secondAnswerText, false);
        addToList(answers, test, thirdAnswerText, false);
        addToList(answers, test, fourthAnswerText, false);
        question.setAnswers(answers);
        QuestionService.createQuestion(question);
        return editor;
    }

    private void addToList(List<Answer> answers, Test test, String text, boolean correct) {
        Answer answer = new Answer();
        answer.setTest(test);
        answer.setDescription(text);
        answer.setCorrect(correct);
        answers.add(answer);
    }
}
