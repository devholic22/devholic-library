package devholic.library.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TokenAgent {

    private static final String RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    public static Map<String, String> getUserResource(String accessToken) throws IOException {
        URL url = new URL(RESOURCE_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to get user resource: " + responseCode);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Map<String, String> info = new HashMap<>();
        while (true) {
            String row = br.readLine();
            if (row == null)
                break;
            String[] each;
            if (row.startsWith("name")) {
                each = row.split(":");
            } else {
                each = row.split(" ");
            }
            if (each.length <= 2)
                continue;
            String key = each[2].replace(":", "");
            key = key.replace("\"", "");
            String value = each[3].replace(",", "");
            value = value.replace("\"", "");
            info.put(key, value);
        }
        return info;
    }
}