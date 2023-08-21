package devholic.library.discordbot;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DiscordSender {

    private final String DISCORD_WEBHOOK;

    public DiscordSender(String WEBHOOK) {
        this.DISCORD_WEBHOOK = WEBHOOK;
    }

    public String send(String content) throws IOException {
        String jsonBody = "{\"content\": \"" + escapeJsonString(content) + "\"}";

        URL discordURL = new URL(DISCORD_WEBHOOK);

        HttpURLConnection connection = (HttpURLConnection) discordURL.openConnection();
        SetConnectionProperty(connection);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            return "send success";
        }
        return "send fail";
    }

    private static void SetConnectionProperty(HttpURLConnection connection) throws ProtocolException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
    }

    private String escapeJsonString(String jsonString) {
        return jsonString
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}