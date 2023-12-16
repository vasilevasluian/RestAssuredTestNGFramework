package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestMethods;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static com.spotify.oauth2.api.TokenManager.renewToken;
import static com.spotify.oauth2.utils.ConfigReader.getInstance;

public class PlaylistApi {
//    public static String access_token = "BQCgDBxufDfXJ5HGcMNUGDUZ3Mdz89rpUecBOhZze5CSrhOX_hIhS6YB7lBFahgMkjbYMFFaBfL4ArxXd4mHIylDMc3jEdm2Wpuo7PIcqBRUQ5pMEsI5jQx5PWCbLZryyb4l6MVNF44ab7QE8I9CrW987pQfSDtVqoS4xKVME0IMeKys6lO0-RHv_-lYaAY-A5ieroO867vJ54McDy3WoaCSH_lSHUB4RTAdkh8HW2Yuv2G79vmc0UXrgjcZ2mw2VU2jdLju8A2BKWURdsmQ";


    public static Response post(Playlist requestPlaylist) {
        return RestMethods.post(requestPlaylist, getToken(),USERS+"/"+getInstance().getUser()+PLAYLISTS );
    }

    public static Response post(Playlist requestPlaylist, String token) {
        return RestMethods.post(requestPlaylist, token,USERS+"/"+getInstance().getUser()+PLAYLISTS );
    }



    public static Response get(String playlistId) {
        return RestMethods.get(getToken(),PLAYLISTS+"/" + playlistId );
    }

    public static Response put(String playlistId, Playlist updatedPlaylist) {
        return RestMethods.put(updatedPlaylist,getToken(),PLAYLISTS+"/" + playlistId );
    }


}
