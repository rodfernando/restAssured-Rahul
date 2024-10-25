package files;

public class Payload {

    public static String addPlace() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public static String courseString() {
        return "{\r\n" + //
                        "\r\n" + //
                        "\"dashboard\": {\r\n" + //
                        "\r\n" + //
                        "\"purchaseAmount\": 910,\r\n" + //
                        "\r\n" + //
                        "\"website\": \"rahulshettyacademy.com\"\r\n" + //
                        "\r\n" + //
                        "},\r\n" + //
                        "\r\n" + //
                        "\"courses\": [\r\n" + //
                        "\r\n" + //
                        "{\r\n" + //
                        "\r\n" + //
                        "\"title\": \"Selenium Python\",\r\n" + //
                        "\r\n" + //
                        "\"price\": 50,\r\n" + //
                        "\r\n" + //
                        "\"copies\": 6\r\n" + //
                        "\r\n" + //
                        "},\r\n" + //
                        "\r\n" + //
                        "{\r\n" + //
                        "\r\n" + //
                        "\"title\": \"Cypress\",\r\n" + //
                        "\r\n" + //
                        "\"price\": 40,\r\n" + //
                        "\r\n" + //
                        "\"copies\": 4\r\n" + //
                        "\r\n" + //
                        "},\r\n" + //
                        "\r\n" + //
                        "{\r\n" + //
                        "\r\n" + //
                        "\"title\": \"RPA\",\r\n" + //
                        "\r\n" + //
                        "\"price\": 45,\r\n" + //
                        "\r\n" + //
                        "\"copies\": 10\r\n" + //
                        "\r\n" + //
                        "}\r\n" + //
                        "\r\n" + //
                        "]\r\n" + //
                        "\r\n" + //
                        "}\r\n" + //
                        "\r\n" + //
                        "";
    }

    public static String addBook() {
        return "{\r\n" + //
                        "\"name\":\"Learn Appium Automation with Java\",\r\n" + //
                        "\"isbn\":\"bcd\",\r\n" + //
                        "\"aisle\":\"2926\",\r\n" + //
                        "\"author\":\"John foer\"\r\n" + //
                        "}";
    }
}
