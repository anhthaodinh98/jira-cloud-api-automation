package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultilities.DataGenerator;

import java.io.IOException;

public class TC004 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    IssuesAPI issuesAPI3 = new IssuesAPI();
    String projectId = "10000";
    String issueTypeStory = "10005";
    String userId = "5e4e646d7ece1a0c9992c6ae";
    String issueId;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        Allure.step("Pre-condition: Run post request");
        issueId = issuesAPI.createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);
    }

    @Test
    @Description("Delete issue")
    public void TC004() {
        Allure.step("Step 1: Run delete issue request");
        issuesAPI2.deleteIssue(issueId);

        Allure.step("Step 2: Verify status code is 204");
        softAssert.assertEquals(issuesAPI2.getResponseStatusCode(), 204);

        Allure.step("Step 3: Run get issue request");
        issuesAPI3.getIssue(issueId);

        Allure.step("Step 4: Assert status code is 404");
        softAssert.assertEquals(issuesAPI3.getResponseStatusCode(), 404);

        softAssert.assertAll();
    }
}
