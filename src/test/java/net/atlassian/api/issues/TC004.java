package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ultilities.DataGenerator;
import ultilities.JsonHelper;

import java.io.IOException;

public class TC004 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    IssuesAPI issuesAPI3 = new IssuesAPI();
    String projectId = JsonHelper.getData("$.projectId");
    String issueTypeStory = JsonHelper.getData("$.issueType.story");
    String userId = JsonHelper.getData("$.userId.admin");
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
