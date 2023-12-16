package spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQAPAjYHodHvZY3SLOSbgG7Qdk4jl-i4CA5e0B50WyG-PSaAm9xHLSx1ZzRngN42CfGcAKk82KQujxm65qyoOoIu98qbonozt8r41V47O1MVYIF3no93mo2i4sGYtqm2zUHE3ecot53LBvG-XN2FU2etxtqnyDDBoTG7Y63Og5rluztykTIcCpK-c8e5S57fDIXJfdQrtLhONh01Vb7CPRwfQJXOQTWAdVmyE-8WFC5WS08AxiOXO8yZd2mZA6wwAHg9UIq03D4E8vFHp14l";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer "+access_token)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
//                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreatePlaylist(){
        String payload = "{\n" +
                "    \"name\": \"New PlaylistTest\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification)
                .body(payload)
                .when().post("users/31tnpumsgx57j5mhxhrammqqgoba/playlists")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .body("name", equalTo("New PlaylistTest"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetAPlaylist(){
        given(requestSpecification)
                .when().get("/playlists/1JLs7xafo9sJYn6Clz13hf")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Updated Playlist Name"),
                        "description",equalTo( "Updated playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist(){
        String payload = "{\n" +
                "    \"name\": \"Vasile Playlist Name\",\n" +
                "    \"description\": \"Vasile playlist description\",\n" +
                "    \"public\": true\n" +
                "}";
        given(requestSpecification)
                .body(payload)
                .when().put("/playlists/1JLs7xafo9sJYn6Clz13hf")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName(){
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(requestSpecification)
                .body(payload)
                .when().post("users/31tnpumsgx57j5mhxhrammqqgoba/playlists")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(400)
                .body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));
    }


    @Test
    public void shouldNotBeAbleToCreatePlaylistWithExpiredToken(){
        String payload = "{\n" +
                "    \"name\": \"New PlaylistTest\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer "+"access_token")
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .when().post("users/31tnpumsgx57j5mhxhrammqqgoba/playlists")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(401)
                .body("error.status", equalTo(401),
                        "error.message", equalTo("Invalid access token"));
    }

}
