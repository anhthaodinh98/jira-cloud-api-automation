package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ultilities.DataGenerator;
import ultilities.JsonHelper;

import java.io.IOException;

public class TC002 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    String projectId = JsonHelper.getData("$.projectId");
    String issueTypeStory = JsonHelper.getData("$.issueType.story");
    String userId = JsonHelper.getData("$.userId.admin");
    String issueId;

    @AfterMethod
    public void afterMethod() {
        Allure.step("Post-condition: Delete issue");
        issuesAPI2.deleteIssue(issueId);
    }

    @Test
    @Description("Create issue")
    public void TC002() throws IOException {
        Allure.step("Step 1: Run create issue request");
        issueId = issuesAPI.createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);

        Allure.step("Step 2: Assert status is 201");
        softAssert.assertEquals(issuesAPI.getResponseStatusCode(), 201);

        Allure.step("Step 3: Assert jira id");
        Object o = issuesAPI.getResponse().path("key");
        softAssert.assertTrue(o.toString().matches("PAP-\\d+"));

        softAssert.assertAll();
    }
}
