package devholic.library.oauth2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class CommonMethod {

    public static void validateConnectionResponseStatus(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to get user resource: " + responseCode);
        }
    }

    public static BufferedReader parseConnectionToBufferedReader(HttpURLConnection connection)
            throws IOException {
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}
