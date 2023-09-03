package net.atlassian.api;

import com.beust.jcommander.internal.Maps;
import com.hubspot.jinjava.Jinjava;
import ultilities.FileReader;

import java.io.IOException;
import java.util.Map;

public class IssuesAPI extends JiraCloudBase {

    private final static String GET_ISSUE_BY_ID = "/issue/{issueId}";
    private final static String POST_CREATE_ISSUE = "/issue";
    private final static String DELETE_ISSUE = "/issue/{issueId}";
    private final static String ASSIGN_ISSUE = "/issue/{issueIdOrKey}/assignee";

    public void getIssue(String issueId) {
        sendRequest(Method.GET, GET_ISSUE_BY_ID, issueId);
    }

    public String createIssue(String projectId, String issueTypeId, String summary, String reporterId) throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("projectId", projectId);
        context.put("issueTypeId", issueTypeId);
        context.put("summary", summary);
        context.put("reporterId", reporterId);

        String template = FileReader.readFile("src/test/resources/templates/payloadCreateIssue.json");
        String renderedTemplate = jinjava.render(template, context);

        this.getRequestSpecification().body(renderedTemplate);
        sendRequest(Method.POST, POST_CREATE_ISSUE);

        return this.getResponse().path("key").toString();
    }

    public void deleteIssue(String issueId) {
        sendRequest(Method.DELETE, DELETE_ISSUE, issueId);
    }

    public void assignIssue(String issueId, String accountId) throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("accountId", accountId);

        String template = FileReader.readFile("src/test/resources/templates/payloadAssignIssue.json");
        String renderedTemplate = jinjava.render(template, context);

        this.getRequestSpecification().body(renderedTemplate);

        sendRequest(Method.PUT, ASSIGN_ISSUE, issueId);
    }
}
