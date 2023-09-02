package devholic.library.oauth2.apple;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AppleTokenAgent {

    public static String getUserResource(String appleToken) {

        String[] parts = appleToken.split("\\.");
        String payload = parts[1];

        String decodedPayload = new String(Base64.getDecoder().decode(payload), StandardCharsets.UTF_8);

        String payloadJson = decodedPayload.substring(1, decodedPayload.length() - 1);
        String[] jsonArray = payloadJson.split(",");
        for (String json : jsonArray) {
            String[] each = json.split(":");
            String key = each[0].substring(1, each[0].length() - 1);
            String value = each[1].substring(1, each[1].length() - 1);
            if (key.equals("email"))
                return value;
        }
        return "fail";
    }
}
