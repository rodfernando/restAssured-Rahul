package oauthtest;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class oAuth {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String responseOAuth = given()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("/oauthapi/oauth2/resourceOwner/token").asString();

        JsonPath js = new JsonPath(responseOAuth);
        String access_token = js.getString("access_token");
        System.out.println(access_token);

        String responseToken = given()
                .queryParams("access_token", access_token)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

        System.out.println(responseToken);
    }
}
