import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.ReusableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

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

        JsonPath js1 = ReusableMethods.rawToJson(response);
        String placeId = js1.getString("place_id");
        System.out.println("Este é o place_id = " + placeId);

    // Update Place
        String newAddress = "70 Summer walk, USA";

                given().
                log().all().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().
                put("/maps/api/place/update/json").
                then().
                // log().all().
                assertThat().
                statusCode(200).
                body("msg", equalTo("Address successfully updated"));


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

        JsonPath js2 = ReusableMethods.rawToJson(getPlaceResponse);
        String updatedAddress = js2.getString("address");
        System.out.println("Este é o endereço atualizado = " + updatedAddress);

        Assert.assertEquals(updatedAddress, newAddress);
    }
}
