package validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class sumValidation {

    @Test
    public void sumOfCourses() {
        JsonPath js = new JsonPath(Payload.courseString());
        int countCourses = js.getInt("courses.size()");

        int sum = 0;
        for (int i = 0; i < countCourses; i++) {
            int copies = js.getInt("courses[" + i + "].copies");
            int prices = js.getInt("courses[" + i + "].price");
            int amount = copies * prices;
            System.out.println(amount);
            sum += amount;
        }
        System.out.println(sum);
        int totalAmountDashboard = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, totalAmountDashboard);
    }
}
