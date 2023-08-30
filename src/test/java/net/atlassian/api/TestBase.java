package net.atlassian.api;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class TestBase {
    protected SoftAssert softAssert;

    @BeforeSuite
    public void beforeTest() {
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void afterTest() {

    }
}
