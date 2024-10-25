import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.ReusableMethods;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String aisle, String isbn) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given()
                .header("Content-Type", "application/json")
                .body(Payload.addBook(aisle, isbn))
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        Object idBook = js.get("ID");
        System.out.println(idBook);

        // Delete book



    }


    @DataProvider(name="BooksData")
    public Object[][] getData() {
        return new Object[][] {{"fqsa", "321321"},{"sdsa", "897889"},{"lkljk", "657765"}};
    }
}
