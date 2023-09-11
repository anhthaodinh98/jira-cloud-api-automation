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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TC006 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssueCommentsAPI issueCommentsAPI = new IssueCommentsAPI();
    String projectId = "10000";
    String issueTypeStory = "10005";
    String userId = "5e4e646d7ece1a0c9992c6ae";
    String issueId;
    List<String> comments = Arrays.asList("first comment", "second comment", "third comment");
    List<String> commentIds = new ArrayList<>();

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Pre-condition: Run post request");
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
    public void TC006() {
        Allure.step("Step 1: Run get all comments for an issue request");
        issueCommentsAPI.getComments(issueId);

        Allure.step("Step 2: Assert status code is 200");
        softAssert.assertEquals(issueCommentsAPI.getResponseStatusCode(), 200);

        Allure.step("Step 3: Assert list comments");
        softAssert.assertTrue(doesListCommentsMatch(comments, issueCommentsAPI.getResponse()));

        softAssert.assertAll();
    }
}
