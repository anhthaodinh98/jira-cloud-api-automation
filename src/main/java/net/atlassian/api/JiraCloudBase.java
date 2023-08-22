package net.atlassian.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraCloudBase {

    protected static RequestSpecification authenticate() {
        return RestAssured.given()
//                .auth().basic("anhthaodinh98@gmail.com","ATATT3xFfGF0wjhLPvPrFQrq4sxXtrLI3aTiF7CsvxAPj7ad6KW2bpwHuLCuCsPbSFBlP6SwCa_AYTXnz_JynyAIdoVIw-ZMe9nMuhnaefevo01fTLH2UBQqKjA1LFOMeT85NkRk1wc9TC_Gf0LBM0CLg5bs4XevB27wMZ9q4ZqugpjRRnRFbA8=3E5C9DAB")
                .header("Authorization","Basic YW5odGhhb2Rpbmg5OEBnbWFpbC5jb206QVRBVFQzeEZmR0Ywd2poTFB2UHJGUXJxNHN4WHRyTEkzYVRpRjdDc3Z4QVBqN2FkNktXMmJwd0h1TEN1Q3NQYlNGQmxQNlN3Q2FfQVlUWG56X0p5bnlBSWRvVkl3LVpNZTluTXVobmFlZmV2bzAxZlRMSDJVQlFxS2pBMUxGT01lVDg1TmtSazF3YzlUQ19HZjBMQk0wQ0xnNWJzNFhldkIyN3dNWjlxNFpxdWdwalJSblJGYkE4PTNFNUM5REFC")
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .baseUri("https://anhthaodinh98.atlassian.net/rest/api/3");
    }

}
