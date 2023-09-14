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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TC005 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssueCommentsAPI issueCommentsAPI = new IssueCommentsAPI();
    String projectId = JsonHelper.getData("$.projectId");
    String issueTypeStory = JsonHelper.getData("$.issueType.story");
    String userId = JsonHelper.getData("$.userId.admin");
    String issueId;
    List<String> comments = Arrays.asList("first comment", "second comment", "third comment");
    List<String> commentIds = new ArrayList<>();

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Pre-condition: Run create issue request");
        issueId = new IssuesAPI().createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);

        Allure.step("Add comment");
        for (String comment : comments) {
            String commentId = new IssueCommentsAPI().addComment(issueId, comment);
            commentIds.add(commentId);
        }
    }

    @AfterMethod
    public void afterMethod() {
        Allure.step("Post-condition: Delete issue");
        issuesAPI.deleteIssue(issueId);
    }

    @Test
    @Description("Get comments")
    public void TC005() {
        Allure.step("Step 1: Run get all comments for an issue request");
        issueCommentsAPI.getComments(issueId);

        Allure.step("Step 2: Assert status code is 200");
        softAssert.assertEquals(issueCommentsAPI.getResponseStatusCode(), 200);

        Allure.step("Step 3: Assert list comments in response equals list expected comments");
        softAssert.assertTrue(doesListCommentsMatch(comments, issueCommentsAPI.getResponse()));

        softAssert.assertAll();
    }
}
