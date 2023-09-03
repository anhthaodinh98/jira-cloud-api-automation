package net.atlassian.api;

import java.io.File;

public class IssueAttachments extends JiraCloudBase{
    private final static String POST_ADD_ATTACHMENT = "/rest/api/3/issue/{issueIdOrKey}/attachments";

    public void addAttachment(String issueId) {
        this.getRequestSpecification()
                .header("X-Atlassian-Token", "no-check")
                .contentType("multipart/form-data")
                .multiPart("file", new File("src/test/resources/attachments/Note.txt"));
        sendRequest(Method.POST, POST_ADD_ATTACHMENT, issueId);
    }
}
