package net.atlassian.api.issues;

import common.Constant.JiraCloud;
import io.restassured.response.Response;
import net.atlassian.api.Issues;
import net.atlassian.api.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ultilities.DataGenerator;

import java.io.IOException;

public class TC001 extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "This is test case 1")
    public void TC001 () throws IOException {
        Response response = Issues.createIssue(JiraCloud.PROJECT_ID, JiraCloud.ISSUE_TYPE_STORY, DataGenerator.randomDescription(), JiraCloud.USER_ID);
        int statusCode = response.statusCode();

        softAssert.assertEquals(statusCode, 201);
        Object o = response.path("key");
        softAssert.assertTrue(o.toString().matches("PAP-\\d+"));
        softAssert.assertAll();
    }
}
