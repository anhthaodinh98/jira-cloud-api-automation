package net.atlassian.api.issuesComments;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssueCommentsAPI;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultilities.DataGenerator;
import ultilities.JsonHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TC007 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    IssueCommentsAPI issueCommentsAPI = new IssueCommentsAPI();
    IssueCommentsAPI issueCommentsAPI2 = new IssueCommentsAPI();
    String projectId = JsonHelper.getData("$.projectId");
    String issueTypeStory = JsonHelper.getData("$.issueType.story");
    String userId = JsonHelper.getData("$.userId.admin");
    String issueId;
    String comment = DataGenerator.randomDescription();
    String commentId;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Pre-condition: Run post request");
        issueId = issuesAPI.createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);

        Allure.step("Add comment");
        commentId = issueCommentsAPI.addComment(issueId, comment);
    }

    @AfterMethod
    public void afterMethod() {
        Allure.step("Post-condition: Delete issue");
        issuesAPI2.deleteIssue(issueId);
    }

    @Test
    @Description("Get comment by id")
    public void TC007() {
        Allure.step("Step 1: Run get comment by id request");
        issueCommentsAPI2.getCommentById(issueId, commentId);

        Allure.step("Step 2: Assert status code is 200");
        softAssert.assertEquals(issueCommentsAPI2.getResponseStatusCode(), 200);

        Allure.step("Step 3: Assert comment content");
        softAssert.assertTrue(doesCommentMatch(comment, issueCommentsAPI2.getResponse()));

        softAssert.assertAll();
    }
}
