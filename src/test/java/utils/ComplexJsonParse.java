package utils;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.courseString());

        // 1º return nº of courses returned by API
        int countCourses = js.getInt("courses.size()");
        System.out.println(countCourses);

        // 2º purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // 3º Print the title of the first course
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println(firstCourseTitle);

        int sumPrices = 0;
        // 4º Print all course titles and their respective Prices
        for (int i = 0; i < countCourses; i++) {
            String coursesTitle = js.getString("courses["+i+"].title");
            int coursesPrice = js.getInt("courses["+i+"].price");
            sumPrices += coursesPrice;
            System.out.println("Course: " + coursesTitle +"\n Price: " + coursesPrice + "\n" + "-".repeat(20));
        }

        // 5º nº of RPA copies
        for(int i = 0; i<countCourses; i++) {
            String coursesTitle = js.get("courses["+i+"].title");
            if(coursesTitle.equalsIgnoreCase("RPA")) {
                Object courseCopies = js.get("courses["+i+"].copies");
                System.out.println(courseCopies);
                break;
            }
        }

        System.out.println(sumPrices);
        
 
    }
}
