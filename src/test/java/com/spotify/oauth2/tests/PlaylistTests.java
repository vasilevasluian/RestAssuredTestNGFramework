package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {


    @Test
    public void shouldBeAbleToCreatePlaylist() {
        Playlist requestPlaylist = Playlist.builder()
                .name("Vasile test patru")
                .description("Vasile description patru")
                ._public(false).build();
//        Playlist requestPlaylist = new Playlist()
//                .setName("Vasile test trei")
//                .setDescription("Vasile description trei")
//                .setPublic(false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(201));
        Playlist playListResponse = response.as(Playlist.class);
        assertThat(playListResponse.getName(), equalTo(requestPlaylist.getName()));
        assertThat(playListResponse.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(playListResponse.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void shouldBeAbleToGetAPlaylist() {
        Playlist requestPlaylist = Playlist.builder()
                .name("Updated Vasile")
                .description("Updated Vasile description")
                ._public(true).build();

        Response response = PlaylistApi.get("3zRFlwdf4YdHKJk7KZbhYz");
        assertThat(response.statusCode(), equalTo(200));
        Playlist playListResponse = response.as(Playlist.class);
        assertThat(playListResponse.getName(), equalTo(requestPlaylist.getName()));
        assertThat(playListResponse.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(playListResponse.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist() {
        Playlist updatedPlaylist = Playlist.builder()
                .name("Updated Vasile")
                .description("Updated Vasile description")
                ._public(true).build();
        Response response = PlaylistApi.put("3zRFlwdf4YdHKJk7KZbhYz", updatedPlaylist);
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlaylist =Playlist.builder()
                .name("")
                .description("Updated Vasile description")
                ._public(true).build();
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(400));
        Error error = response.as(Error.class);
        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }


    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        Playlist requestPlaylist =Playlist.builder()
                .name("Updated Vasile")
                .description("Updated Vasile description")
                ._public(false).build();
        Response response = PlaylistApi.post(requestPlaylist, "123213123");
        assertThat(response.statusCode(), equalTo(401));
        Error error = response.as(Error.class);
        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }

}
