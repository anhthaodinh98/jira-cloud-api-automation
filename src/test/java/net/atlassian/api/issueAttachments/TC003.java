package net.atlassian.api.issueAttachments;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import net.atlassian.api.IssueAttachments;
import net.atlassian.api.TestBase;
import org.testng.annotations.Test;

public class TC003 extends TestBase {

    String issueId = "PAP-1";

    @Test(description = "This is test case 3")
    @Description("This is test case 3 allure")
    public void TC003() {
//        Allure.step("Step 1: run post request");
//        Response response = IssueAttachments.addAttachment(issueId);
//        int statusCode = response.statusCode();
//        Allure.step("Step 2: Assert status is 200");
//        softAssert.assertEquals(statusCode, 200);
//        softAssert.assertAll();
    }
}
