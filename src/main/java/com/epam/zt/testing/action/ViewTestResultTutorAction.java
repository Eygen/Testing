package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Question;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.QuestionService;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewTestResultTutorAction implements Action {
    private ActionResult result = new ActionResult("viewTestResults");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String testId = req.getParameter("test_id");
        String studentId = req.getParameter("student_id");
        Test test = TestService.findById(Integer.parseInt(testId));
        Student student = StudentService.findById(Integer.parseInt(studentId));
        List<Question> questions = QuestionService.findStudentResult(student, test);
        test.setQuestions(questions);
        req.getSession().setAttribute("passedTest", test);
        return result;
    }
}
