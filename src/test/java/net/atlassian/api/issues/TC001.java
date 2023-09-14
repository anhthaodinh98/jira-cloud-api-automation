package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import net.atlassian.api.IssuesAPI;
import net.atlassian.api.TestBase;
import org.testng.annotations.Test;

public class TC001 extends TestBase {
    IssuesAPI issuesAPI = new IssuesAPI();
    IssuesAPI issuesAPI2 = new IssuesAPI();
    String issueId = "PAP-1";

    @Test
    @Description("Get existed and non-existed issues")
    public void TC001() {
        Allure.step("Step 1: Run get issue request");
        issuesAPI.getIssue(issueId);

        Allure.step("Step 2: Assert status code is 200");
        softAssert.assertEquals(issuesAPI.getResponseStatusCode(), 200);

        Allure.step("Step 3: Assert jira id");
        Object o = issuesAPI.getResponse().path("key");
        softAssert.assertEquals(o.toString(), issueId);

        Allure.step("Step 4: Run get issue request with a non-existed issue");
        issuesAPI2.getIssue("NON-123");

        Allure.step("Step 5: Assert status code is 404");
        softAssert.assertEquals(issuesAPI2.getResponseStatusCode(), 404);

        softAssert.assertAll();
    }
}
