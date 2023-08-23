package net.atlassian.api.issues;

import common.Constant.JiraCloud;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import net.atlassian.api.Issues;
import net.atlassian.api.TestBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ultilities.DataGenerator;

import java.io.IOException;

public class TC002 extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "This is test case 2")
    @Description("This is test case 2 allure")
    public void TC001 () throws IOException {
        Allure.step("Step 1: run post request");
        Response response = Issues.createIssue(JiraCloud.PROJECT_ID, JiraCloud.ISSUE_TYPE_STORY, DataGenerator.randomDescription(), JiraCloud.USER_ID);
        int statusCode = response.statusCode();
        Allure.step("Step 2: Assert status is 200");
        softAssert.assertEquals(statusCode, 200);
        Object o = response.path("key");
        softAssert.assertAll();
    }
}
