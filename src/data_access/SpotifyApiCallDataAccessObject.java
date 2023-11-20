package data_access;

import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

// JSON Array
import org.json.JSONObject;


public class SpotifyApiCallDataAccessObject {

    /**
     * Returns the access token. The access token is a string which contains the credentials and permissions that can be used to access resources.
     * The access token is valid for 1 hour. After that time, the token expires and you need to request a new one.
     * More info is located here: https://developer.spotify.com/documentation/web-api/concepts/access-token
     *
     * @return A string containing the temporary access token.
     * @throws Exception if access token cannot be retrieved.
     */

    public static String getAccessToken() {
        // Client ID and Client Secret from Spotify Dashboard
        String clientId = "9ed5f6af048844e4851425fbc416ae10";
        String clientSecret = "df75314d40634c9db0d1da481a2302e8";

        // Spotify API endpoints
        String tokenUrl = "https://accounts.spotify.com/api/token";

        // Base64 encode the client ID and client secret
        String credentials = clientId + ":" + clientSecret;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Build the authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        // Build the request body for token retrieval
        String requestBody = "grant_type=client_credentials";

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response to extract the access token
            String accessToken = response.body().split("\"access_token\":\"")[1].split("\"")[0];

            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUserId(String accessToken) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/me"))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            String userId = jsonResponse.getString("id");

            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    public static JsonArray getUserProfile(String userID) {
//        // Client ID and Client Secret from Spotify Dashboard
//        String clientId = "9ed5f6af048844e4851425fbc416ae10";
//        String clientSecret = "df75314d40634c9db0d1da481a2302e8";
//
//
//    }

    // Used for printing the access token
    public static void main(String[] args) {
        String accessToken = getAccessToken();

//        if (accessToken != null) {
//            System.out.println("Access Token: " + accessToken);
//        } else {
//            System.out.println("Failed to retrieve access token");
//        }
        String userId = getUserId(accessToken);
        System.out.println("User ID: " + userId);
    }

}
