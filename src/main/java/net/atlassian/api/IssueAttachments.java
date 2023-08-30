package net.atlassian.api;

import io.restassured.response.Response;
import java.io.File;

public class IssueAttachments extends JiraCloudBase{
    private final static String POST_ADD_ATTACHMENT = "/rest/api/3/issue/{issueIdOrKey}/attachments";

    public static Response addAttachment(String issueId) {
        Response response = authenticate()
                .header("X-Atlassian-Token", "no-check")
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/resources/attachments/Note.txt"))
                .post(POST_ADD_ATTACHMENT, issueId);
        return response;
    }
}
