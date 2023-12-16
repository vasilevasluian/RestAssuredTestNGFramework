package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {
//    public static String access_token = "BQCgDBxufDfXJ5HGcMNUGDUZ3Mdz89rpUecBOhZze5CSrhOX_hIhS6YB7lBFahgMkjbYMFFaBfL4ArxXd4mHIylDMc3jEdm2Wpuo7PIcqBRUQ5pMEsI5jQx5PWCbLZryyb4l6MVNF44ab7QE8I9CrW987pQfSDtVqoS4xKVME0IMeKys6lO0-RHv_-lYaAY-A5ieroO867vJ54McDy3WoaCSH_lSHUB4RTAdkh8HW2Yuv2G79vmc0UXrgjcZ2mw2VU2jdLju8A2BKWURdsmQ";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();

    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://accounts.spotify.com/")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL).build();

    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL).build();
    }
}
