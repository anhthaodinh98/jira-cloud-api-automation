package ultilities;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHelper {

    // TODO implement more if needed

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    public static JSONObject parseString(String text) throws JSONException {
        JSONObject jsonObject = new JSONObject(text);
        return jsonObject;
    }
}
