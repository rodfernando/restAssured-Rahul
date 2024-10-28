package jira_api;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.ReusableMethods;

public class BugJiraTest {
    public static void main(String[] args) {
        
        //Load config.properties here:
        Properties prop = new Properties();
        String authToken = prop.getProperty("AUTH_TOKEN");
        String baseUrl = prop.getProperty("BASE_URL");


        RestAssured.baseURI= baseUrl;
        String bugJiraResponse = given()
        .header("Content-Type","application/json")
        .header("Authorization",authToken)
        .body("{\r\n" + //
                        "    \"fields\": {\r\n" + //
                        "       \"project\":\r\n" + //
                        "       {\r\n" + //
                        "          \"key\": \"SCRUM\"\r\n" + //
                        "       },\r\n" + //
                        "       \"summary\": \"Segundou!\",\r\n" + //
                        "       \"issuetype\": {\r\n" + //
                        "          \"name\": \"Bug\"\r\n" + //
                        "       }\r\n" + //
                        "   }\r\n" + //
                        "}")
        .log().all()
        .when()
        .post("/rest/api/3/issue")
        .then()
        .log().all().assertThat().statusCode(201)
        .extract().response().asString();

        JsonPath jsonData = ReusableMethods.rawToJson(bugJiraResponse);
        String issueId = jsonData.get("id");
        System.out.println("aqui está o id do bug criado:" + issueId);    

    }

}

//Accept:application/json
