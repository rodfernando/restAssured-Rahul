package pojoclasses;

import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.ArrayList;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoclasses.pojo.AddPlace;
import pojoclasses.pojo.Location;

public class SpecBuilderTest {
    public static void main(String[] args) {

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


        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        RequestSpecification res = given().spec(req).body(p);

        Response response = res.when()
        .post("/maps/api/place/add/json")
        .then().spec(resSpec)
        .extract().response();

        String responseString = response.asPrettyString();
        System.out.println(responseString);
    }
}
