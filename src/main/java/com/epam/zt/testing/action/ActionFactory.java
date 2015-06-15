package com.epam.zt.testing.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/studentHome", new GetHomeStudentAction());
        actions.put("GET/registry", new ShowPageAction("registry"));
        actions.put("POST/registry", new RegistryAction());
        actions.put("GET/registrySuccess", new ShowPageAction("registrySuccess"));
        actions.put("GET/studentSettings", new GetUpdateStudentAction());
        actions.put("POST/studentSettings", new PostUpdateStudentAction());
        actions.put("GET/startTest", new GetStartTestAction());
        actions.put("POST/startTest", new PostStartTestAction());
        actions.put("GET/passTest", new GetPassTestAction());
        actions.put("POST/passTest", new PostPassTestAction());
        actions.put("GET/finishTest", new FinishTestAction());
        actions.put("GET/viewMark", new ViewMarkAction());
        actions.put("GET/pageMark", new MarkPageAction());

        actions.put("GET/tutorHome", new GetHomeTutorAction());
        actions.put("GET/tutorSettings", new GetUpdateTutorAction());
        actions.put("POST/tutorSettings", new PostUpdateTutorAction());
        actions.put("GET/createTest", new GetCreateTestAction());
        actions.put("POST/createTest", new PostCreateTestAction());
        actions.put("GET/testEditor", new GetTestEditorAction());
        actions.put("POST/testEditor", new PostTestEditorAction());
        actions.put("GET/editTest", new GetEditTestTutorAction());
        actions.put("POST/editTest", new PostEditTestTutorAction());
        actions.put("GET/assignTest", new GetAssignTestAction());
        actions.put("GET/studentDetails", new StudentDetailsAction());
        actions.put("POST/assignGroupTest", new GroupAssignTestAction());
        actions.put("POST/assignStudentTest", new StudentAssignTestAction());
        actions.put("POST/findStudent", new FindStudentForAssignAction());
        actions.put("POST/blockTest", new BlockTestAction());
        actions.put("GET/testResults", new GetTestResultsAction());
        actions.put("POST/findTestResults", new FindTestResultsAction());
        actions.put("GET/viewTestResults", new ViewTestResultTutorAction());

        actions.put("GET/adminHome", new ShowPageAction("adminHome"));
        actions.put("GET/adminSettings", new GetUpdateAdminAction());
        actions.put("POST/adminSettings", new PostUpdateAdminAction());
        actions.put("GET/userCategory", new GetUserCategoryAction());
        actions.put("GET/pageUserCategory", new UserCategoryPageAction());
        actions.put("GET/personDetails", new UserCategoryDetailsAction());
        actions.put("POST/userCategoryFind", new UserCategoryFindAction());
        actions.put("POST/userCategoryChangeRole", new UserCategoryChangeRoleAction());
        actions.put("POST/userCategoryDelete", new UserCategoryDeleteAction());
        actions.put("GET/groupCategory", new GetGroupCategoryAction());
        actions.put("GET/pageGroupCategory", new GroupCategoryPageAction());
        actions.put("GET/groupDetails", new GroupCategoryDetailsAction());
        actions.put("POST/groupCategoryFind", new GroupCategoryFindAction());
        actions.put("POST/groupCategoryDelete", new GroupCategoryDeleteAction());
        actions.put("POST/groupCategoryAdd", new GroupCategoryAddAction());
        actions.put("GET/subjectCategory", new GetSubjectCategoryAction());
        actions.put("GET/pageSubjectCategory", new SubjectCategoryPageAction());
        actions.put("GET/subjectDetails", new SubjectCategoryDetailsAction());
        actions.put("POST/subjectCategoryFind", new SubjectCategoryFindAction());
        actions.put("POST/subjectCategoryDelete", new SubjectCategoryDeleteAction());
        actions.put("POST/subjectCategoryAdd", new SubjectCategoryAddAction());
        actions.put("GET/testCategory", new GetTestCategoryAction());
        actions.put("POST/testCategoryFindBySubject", new TestCategoryFindBySubjectAction());
        actions.put("POST/testCategoryFindByStudent", new TestCategoryFindByStudentAction());
        actions.put("POST/testCategoryDelete", new TestCategoryDeleteAction());
        actions.put("GET/viewTest", new GetViewTestAdminAction());
        actions.put("POST/deleteTest", new DeleteTestAdminAction());
        actions.put("GET/viewStudentTest", new GetStudentTestAdminAction());
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
