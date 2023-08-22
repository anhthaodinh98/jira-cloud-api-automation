package net.atlassian.api;

import com.beust.jcommander.internal.Maps;
import com.hubspot.jinjava.Jinjava;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Issues extends JiraCloudBase {

    private final static String GET_ISSUE_BY_ID = "/issue/{issueId}";
    private final static String POST_CREATE_ISSUE = "/issue";

    public static Response getIssue(String issueId) {
        Response response = authenticate().get(GET_ISSUE_BY_ID, issueId);
        System.out.println(response.getBody().asString());
        return response;
    }

    public static Response createIssue(String projectId, String issueTypeId, String summary, String reporterId) throws IOException {
        Jinjava jinjava = new Jinjava();
        Map<String, Object> context = Maps.newHashMap();
        context.put("projectId", projectId);
        context.put("issueTypeId", issueTypeId);
        context.put("summary", summary);
        context.put("reporterId", reporterId);

        String template = new String(Files.readAllBytes(Paths.get("src/test/resources/templates/payloadCreateIssue.json")));
        String renderedTemplate = jinjava.render(template, context);

        Response response = authenticate()
                .body(renderedTemplate)
                .post(POST_CREATE_ISSUE);
        System.out.println("Code " + response.statusCode());
        System.out.println("Response" + response.getBody().asString());
        return response;
    }

}
