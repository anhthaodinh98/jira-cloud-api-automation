package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultilities.DataGenerator;
import ultilities.JsonHelper;

import java.io.IOException;

public class TC004 extends TestBase {

    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    IssuesAPI issuesAPI3 = new IssuesAPI();
    IssuesAPI issuesAPI4 = new IssuesAPI();
    String projectId = JsonHelper.getData("$.projectId");
    String issueTypeStory = JsonHelper.getData("$.issueType.story");
    String userId = JsonHelper.getData("$.userId.admin");
    String issueId;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Pre-condition: Run post request");
        issueId = issuesAPI.createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);
    }

    @AfterMethod
    public void afterMethod() {
        Allure.step("Post-condition: Delete issue");
        issuesAPI4.deleteIssue(issueId);
    }

    @Test
    @Description("Assign issue")
    public void TC004() throws IOException {
        Allure.step("Step 1: Run put request");
        issuesAPI2.assignIssue(issueId, userId);

        Allure.step("Step 2: Verify status code is 204");
        softAssert.assertEquals(issuesAPI2.getResponseStatusCode(), 204);

        Allure.step("Step 3: Run get issue request");
        issuesAPI3.getIssue(issueId);

        Allure.step("Step 4: Verify response contains assignee");
        Object o = issuesAPI3.getResponse().path("fields.assignee.accountId");
        softAssert.assertEquals(o.toString(), userId);

        softAssert.assertAll();
    }
}
