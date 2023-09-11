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

import java.io.IOException;

public class TC008 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    IssueCommentsAPI issueCommentsAPI = new IssueCommentsAPI();
    IssueCommentsAPI issueCommentsAPI2 = new IssueCommentsAPI();
    IssueCommentsAPI issueCommentsAPI3 = new IssueCommentsAPI();
    String projectId = "10000";
    String issueTypeStory = "10005";
    String userId = "5e4e646d7ece1a0c9992c6ae";
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
    @Description("Delete comment")
    public void TC008() {
        Allure.step("Step 1: Run delete comment request");
        issueCommentsAPI2.deleteComment(issueId, commentId);

        Allure.step("Step 2: Assert status code is 204");
        softAssert.assertEquals(issueCommentsAPI2.getResponseStatusCode(), 204);

        Allure.step("Step 3: Run get comment by id request");
        issueCommentsAPI3.getCommentById(issueId, commentId);

        Allure.step("Step 4: Assert status code is 404");
        softAssert.assertEquals(issueCommentsAPI3.getResponseStatusCode(), 404);

        softAssert.assertAll();
    }
}
