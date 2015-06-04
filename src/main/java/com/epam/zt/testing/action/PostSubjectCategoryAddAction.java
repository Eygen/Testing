package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Subject;
import com.epam.zt.testing.model.Tutor;
import com.epam.zt.testing.service.SubjectService;
import com.epam.zt.testing.service.TutorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostSubjectCategoryAddAction implements Action {
    private ActionResult subjectCategory = new ActionResult("subjectCategory?addSuccess=1", true);
    private ActionResult subject = new ActionResult("subjectCategory");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String subjectName = req.getParameter("newSubject");
        String tutorLastname = req.getParameter("tutorLastname");
        String tutorFirstname = req.getParameter("tutorFirstname");
        Tutor tutor = TutorService.findByName(tutorLastname, tutorFirstname);
        if (tutor == null) {
            req.setAttribute("tutorError", "No such tutor!");
            return subject;
        }
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setTutor(tutor);
        SubjectService.createSubject(subject);
        req.setAttribute("addSuccess", "Subject successfully created!");
        return subjectCategory;
    }
}
