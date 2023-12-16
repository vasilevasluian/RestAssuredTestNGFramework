package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestMethods {

    public static Response post(Object requestPlaylist, String token, String path) {
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String token, String path) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(Object updatedPlaylist, String token, String path) {
        return given(getRequestSpec())
                .body(updatedPlaylist)
                .header("Authorization", "Bearer " + token)
                .when().put(path)
                .then().spec(getResponseSpec()).extract().response();

    }

    public static Response postAccount(HashMap<String, String> formParams) {
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .log().all()
                .when().post(API+TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();

    }


}
