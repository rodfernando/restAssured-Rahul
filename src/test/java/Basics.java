import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Basics {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().
                log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(Payload.addPlace()).
                when().
                post("/maps/api/place/add/json").
                then().
//                log().all().
                assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").
                extract().response().asString();// extrair todo o corpo da resposta. Tem que colocar numa variável

        System.out.println(response);

        JsonPath js = new JsonPath(response); // for parsing Json
        String placeId = js.getString("place_id");
        System.out.println("Este é o place_id = " + placeId);

    // Update Place
        String newAddress = "70 Summer walk, USA";

        String testeJson = given().
                log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().
                put("/maps/api/place/update/json").
                then().
                // log().all().
                assertThat().
                statusCode(200).
                body("msg", equalTo("Address successfully updated")).extract().response().asString();

        JsonPath js2 = new JsonPath(testeJson);
        String endereco = js2.getString("address");

        // Get Place
        String getPlaceResponse = given().
                log().all().
                queryParam("key", "qaclick123").
                queryParam("place_id", placeId).
                when().
                get("/maps/api/place/get/json").
                then().
                assertThat().statusCode(200).
                extract().response().asString();

        JsonPath js3 = new JsonPath(getPlaceResponse);
        String updatedAddress = js3.getString("address");
        System.out.println("Este é o endereço atualizado = " + updatedAddress);
    }
}
