package org.example;

import net.atlassian.api.JiraCloudService;

public class Main {
    public static void main(String[] args) {
        JiraCloudService.getIssue("PAP-1");
    }
}