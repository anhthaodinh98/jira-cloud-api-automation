package ultilities;


import com.jayway.jsonpath.JsonPath;

import java.io.IOException;

public class JsonHelper {
    private static final String DATA_JSON_FILE = "src/test/resources/data_test/data.json";

    public static <T> T getData(String jsonFilePath, String jsonPath) {
        String jsonString = null;
        try {
            jsonString = FileReader.readFile(jsonFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JsonPath.read(jsonString, jsonPath);
    }

    public static <T> T getData(String jsonPath) {
        return getData(DATA_JSON_FILE, jsonPath);
    }
}
