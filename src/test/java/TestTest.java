import net.atlassian.api.Issues;

import java.io.IOException;

public class TestTest {
    public static void main(String[] args) throws IOException {
        Issues.createIssue("10000","10005", "haha", "5e4e646d7ece1a0c9992c6ae");
        Issues.getIssue("PAP-3");
    }
}
