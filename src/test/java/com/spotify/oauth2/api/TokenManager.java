package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.utils.ConfigReader.getInstance;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public static String getToken() {

        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renew Token");

                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get token");
        }

        return access_token;
    }

    public static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", getInstance().getClientId());
        formParams.put("client_secret", getInstance().getClientSecret());
        formParams.put("grant_type", getInstance().getGrantType());
        formParams.put("refresh_token", getInstance().getRefreshToken());

        Response response = RestMethods.postAccount(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew Token Failed");
        }

        return response;
    }
}
