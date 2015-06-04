package com.epam.zt.testing.action;

import com.epam.zt.testing.model.Group;
import com.epam.zt.testing.model.Student;
import com.epam.zt.testing.model.Test;
import com.epam.zt.testing.service.GroupService;
import com.epam.zt.testing.service.StudentService;
import com.epam.zt.testing.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GroupAssignTestAction implements Action {
    private ActionResult assignTest = new ActionResult("assignTest?success=1", true);
    private ActionResult assignAgain = new ActionResult("assignTest");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Test test = (Test) req.getSession().getAttribute("test");
        String groupName = req.getParameter("group");
        Group group = GroupService.findByName(groupName);
        List<Student> students = StudentService.findByGroup(group);
        if (students.size() == 0) {
            req.setAttribute("assignError", "Group doesn't have students!");
            return assignAgain;
        } else {
            for (Student student : students) {
                TestService.assignTest(student, test);
            }
        }
        return assignTest;
    }
}
