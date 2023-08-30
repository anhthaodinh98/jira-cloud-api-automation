package net.atlassian.api.issues;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import net.atlassian.api.Issues;
import net.atlassian.api.TestBase;
import org.testng.annotations.Test;
import ultilities.DataGenerator;

import java.io.IOException;

public class TC002 extends TestBase {
    String projectId = "10000";
    String issueTypeStory = "10005";
    String userId = "5e4e646d7ece1a0c9992c6ae";

    @Test(description = "This is test case 2")
    @Description("This is test case 2 allure")
    public void TC002 () throws IOException {
        Allure.step("Step 1: run post request");
        Response response = Issues.createIssue(projectId, issueTypeStory, DataGenerator.randomDescription(), userId);
        int statusCode = response.statusCode();
        Allure.step("Step 2: Assert status is 200");
        softAssert.assertEquals(statusCode, 200);
        Object o = response.path("key");
        softAssert.assertAll();
    }
}
