package net.atlassian.api;

import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestBase {
    protected SoftAssert softAssert;

    @BeforeMethod
    public void beforeTest() {
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void afterTest() {

    }

    /**
     * Use for getComments(issueId)
     * @param expectedComments
     * @param response
     * @return
     */
    public boolean doesListCommentsMatch(List<String> expectedComments, Response response) {
        List responseCommentsRaw = response.path("comments.body.content.content.text");
        List<String> responseComments = new ArrayList<>();
        for (Object object:responseCommentsRaw) {
            for (Object object1:(List)object) {
                for (Object object2:(List)object1) {
                    responseComments.add(object2.toString());
                }
            }
        }
        boolean result = (new HashSet<>(responseComments)).equals(new HashSet<>(expectedComments));
        if (!result) {
            throw new AssertionError(String.format("[%s] does not equal [%s]", responseComments, expectedComments));
        }
        return true;
    }

    /**
     * Use for getCommentById(issueId, commentId)
     * @param expectedComment
     * @param response
     * @return
     */
    public boolean doesCommentMatch(String expectedComment, Response response) {
        List responseCommentsRaw = response.path("body.content.content.text");
        String responseComments = null;
        for (Object object:responseCommentsRaw) {
            for (Object object1:(List)object) {
                responseComments=object1.toString();
            }
        }
        boolean result = responseComments.equals(expectedComment);
        if (!result) {
            throw new AssertionError(String.format("[%s] does not equal [%s]", responseComments, expectedComment));
        }
        return true;
    }
}
