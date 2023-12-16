package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + access_token)
                .when().post("users/31tnpumsgx57j5mhxhrammqqgoba/playlists")
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization", "Bearer " + token)
                .when().post("users/31tnpumsgx57j5mhxhrammqqgoba/playlists")
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String playlistId) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + access_token)
                .when().get("/playlists/" + playlistId)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String playlistId, Playlist updatedPlaylist) {
        return given(getRequestSpec())
                .body(updatedPlaylist)
                .header("Authorization", "Bearer " + access_token)
                .when().put("/playlists/" + playlistId)
                .then().spec(getResponseSpec()).extract().response();

    }


}
