package devholic.library.oauth2.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static devholic.library.oauth2.CommonMethod.parseConnectionToBufferedReader;
import static devholic.library.oauth2.CommonMethod.validateConnectionResponseStatus;

public class GoogleTokenAgent {

    private static final String RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    public static Map<String, String> getUserResource(String accessToken) throws IOException {
        URL url = new URL(RESOURCE_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setConnectionProperties(accessToken, connection);

        validateConnectionResponseStatus(connection);

        BufferedReader br = parseConnectionToBufferedReader(connection);
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

    private static void setConnectionProperties(String accessToken, HttpURLConnection connection)
            throws ProtocolException {
        final String AUTHORIZATION_TOKEN = "Bearer " + accessToken;

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", AUTHORIZATION_TOKEN);
    }
}