package pojoclasses;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.ArrayList;
import io.restassured.RestAssured;
import pojoclasses.pojo.AddPlace;
import pojoclasses.pojo.Location;

public class SerializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhoneNumber("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName("Frontline house");
        List<String> myListTypes = new ArrayList<String>();
        myListTypes.add("shoe park");
        myListTypes.add("shop");
        p.setTypes(myListTypes);
        
        Location l1 = new Location();
        l1.setLat(-38.383494);
        l1.setLng(33.427362);
        p.setLocation(l1);

        String response = given()
        .queryParam("key", "qaclick123")
        .body(p)
        .when()
        .post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200)
        .extract().response().asString();

        System.out.println(response);
    }

}
