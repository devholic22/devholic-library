package devholic.library.oauth2.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static devholic.library.oauth2.CommonMethod.parseConnectionToBufferedReader;
import static devholic.library.oauth2.CommonMethod.validateConnectionResponseStatus;

public class GithubTokenAgent {

    private static final String GITHUB_URL = "https://api.github.com/user";

    public static Map<String, String> getUserResource(String accessToken) throws IOException {

        URL url = new URL(GITHUB_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        setConnectionProperties(accessToken, connection);
        validateConnectionResponseStatus(connection);

        Map<String, String> info = new HashMap<>();
        BufferedReader br = parseConnectionToBufferedReader(connection);
        String jsonBody = br.readLine();

        parseStringToJson(jsonBody, info);

        connection.disconnect();

        return info;
    }

    private static void setConnectionProperties(String accessToken, HttpURLConnection connection)
            throws ProtocolException {
        final String APPLICATION_JSON = "application/json";
        final String AUTHORIZATION_TOKEN = "token " + accessToken;

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", APPLICATION_JSON);
        connection.setRequestProperty("Authorization", AUTHORIZATION_TOKEN);
    }

    private static void parseStringToJson(String jsonString, Map<String, String> dataMap) {
        final String BIO_REGEX = "\\\\r\\\\n";
        final String JSON_REGEX = "\"(.*?)\":\\s*\"(.*?)\"";
        String[] keyValuePairs = jsonString.split(",");

        Pattern pattern = Pattern.compile(JSON_REGEX);
        for (String pair : keyValuePairs) {
            Matcher matcher = pattern.matcher(pair);
            if (!matcher.find())
                continue;
            String key = matcher.group(1);
            String value = matcher.group(2);
            if (isKeyEqualToBio(key)) {
                String[] bioValues = value.split(BIO_REGEX);
                StringBuilder sb = new StringBuilder();
                storeBioValueInStringBuilder(bioValues, sb);
                String parsedValue = sb.toString().trim();
                dataMap.put(key, parsedValue);
            }
            if (isKeyNotEqualToBio(key)) {
                dataMap.put(key, value);
            }
        }
    }

    private static void storeBioValueInStringBuilder(String[] bioValues, StringBuilder sb) {
        for (String bioValue : bioValues) {
            bioValue = bioValue.trim();
            sb.append(bioValue).append(" ");
        }
    }

    private static boolean isKeyEqualToBio(String key) {
        return key.equals("bio");
    }

    private static boolean isKeyNotEqualToBio(String key) {
        return !key.equals("bio");
    }
}
