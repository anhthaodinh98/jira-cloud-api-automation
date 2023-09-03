package net.atlassian.api;

import common.Constant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JiraCloudBase {
    private int responseStatusCode;
    private String responseBody;
    private RequestSpecification requestSpecification;
    private Response response;

    public JiraCloudBase() {
        authenticate();
    }

    private void authenticate() {
        this.requestSpecification = RestAssured.given()
                .header("Authorization", Constant.JiraCloud.TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .baseUri(Constant.JiraCloud.URI);
    }

    protected void sendRequest(Method method, String pathUrl, String... args) {
        switch (method) {
            case GET:
                this.response = this.requestSpecification.get(pathUrl, args);
                this.responseStatusCode = this.response.statusCode();
                this.responseBody = this.response.getBody().asString();
                break;
            case PUT:
                this.response = this.requestSpecification.put(pathUrl, args);
                this.responseStatusCode = this.response.statusCode();
                this.responseBody = this.response.getBody().asString();
                break;
            case POST:
                this.response = this.requestSpecification.post(pathUrl, args);
                this.responseStatusCode = this.response.statusCode();
                this.responseBody = this.response.getBody().asString();
                break;
            case DELETE:
                this.response = this.requestSpecification.delete(pathUrl, args);
                this.responseStatusCode = this.response.statusCode();
                this.responseBody = this.response.getBody().asString();
                break;
            default:
        }
    }

    public enum Method {
        GET,
        POST,
        DELETE,
        PUT
    }
}
