package net.atlassian.api;

import com.beust.jcommander.internal.Maps;
import com.hubspot.jinjava.Jinjava;
import ultilities.FileReader;

import java.io.IOException;
import java.util.Map;

public class IssueCommentsAPI extends JiraCloudBase {
    private final static String GET_COMMENTS = "/issue/{issueId}/comment";
    private final static String GET_COMMENT_BY_ID = "/issue/{issueId}/comment/{id}";
    private final static String POST_ADD_COMMENT = "/issue/{issueId}/comment";
    private final static String PUT_UPDATE_COMMENT = "/issue/{issueId}/comment/{id}";
    private final static String DELETE_COMMENT = "/issue/{issueId}/comment/{id}";

    public void getComments(String issueId) {
        sendRequest(Method.GET, GET_COMMENTS, issueId);
    }

    public void getCommentById(String issueId, String commentId) {
        sendRequest(Method.GET, GET_COMMENT_BY_ID, issueId, commentId);
    }

    public String addComment(String issueId, String comment) throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("comment", comment);

        String template = FileReader.readFile("src/test/resources/templates/payloadCreateComment.json");
        String renderedTemplate = jinjava.render(template, context);

        this.getRequestSpecification().body(renderedTemplate);
        sendRequest(Method.POST, POST_ADD_COMMENT, issueId);

        return this.getResponse().path("id").toString();
    }

    public void updateComment(String issueId, String commentId, String comment) throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("comment", comment);

        String template = FileReader.readFile("src/test/resources/templates/payloadCreateComment.json");
        String renderedTemplate = jinjava.render(template, context);

        this.getRequestSpecification().body(renderedTemplate);
        sendRequest(Method.PUT, PUT_UPDATE_COMMENT, issueId, commentId);
    }

    public void deleteComment(String issueId, String commentId) {
        sendRequest(Method.DELETE, DELETE_COMMENT, issueId, commentId);
    }
}
