package net.atlassian.api;

import common.Constant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraCloudBase {

    protected static RequestSpecification authenticate() {
        return RestAssured.given()
                .header("Authorization", Constant.JiraCloud.TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .baseUri(Constant.JiraCloud.URI);
    }

}
