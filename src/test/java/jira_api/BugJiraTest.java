package jira_api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.ReusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BugJiraTest {

    public static void main(String[] args) {
        // Carrega as propriedades do arquivo config.properties
        Properties prop = loadProperties("src\\test\\resources\\config.properties");

        // Recupera os valores das propriedades e configura a URI base do RestAssured
        String authToken = prop.getProperty("AUTH_TOKEN");
        String baseUrl = prop.getProperty("BASE_URL");
        RestAssured.baseURI = baseUrl;

        // Cria uma nova issue no Jira e obtém o ID do bug criado
        String issueId = createIssue(authToken);

        // Adiciona um anexo à issue criada
        addAttachment(issueId, authToken);
    }

    /**
     * Carrega as propriedades do arquivo de configuração.
     *
     * @param configPath Caminho para o arquivo config.properties.
     * @return Propriedades carregadas.
     */
    private static Properties loadProperties(String configPath) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
            System.out.println("AUTH_TOKEN: " + prop.getProperty("AUTH_TOKEN"));
            System.out.println("BASE_URL: " + prop.getProperty("BASE_URL"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o arquivo config.properties. Verifique o caminho e permissões.");
        }
        return prop;
    }

    /**
     * Cria uma nova issue do tipo "Bug" no Jira.
     *
     * @param authToken Token de autorização.
     * @return ID da issue criada.
     */
    private static String createIssue(String authToken) {
        String response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", authToken)
                .body("{\r\n" +
                        "    \"fields\": {\r\n" +
                        "       \"project\": {\r\n" +
                        "          \"key\": \"SCRUM\"\r\n" +
                        "       },\r\n" +
                        "       \"summary\": \"Teste Attachment\",\r\n" +
                        "       \"issuetype\": {\r\n" +
                        "          \"name\": \"Bug\"\r\n" +
                        "       }\r\n" +
                        "   }\r\n" +
                        "}")
                .log().all()
                .when()
                .post("/rest/api/3/issue")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath jsonData = ReusableMethods.rawToJson(response);
        String issueId = jsonData.getString("id");
        System.out.println("Aqui está o ID do bug criado: " + issueId);
        return issueId;
    }

    /**
     * Adiciona um anexo a uma issue no Jira.
     *
     * @param issueId   ID da issue onde o anexo será adicionado.
     * @param authToken Token de autorização.
     */
    private static void addAttachment(String issueId, String authToken) {
        given()
                .pathParam("issueIdOrKey", issueId)
                .header("Content-Type", "multipart/form-data")
                .header("Authorization", authToken)
                .header("X-Atlassian-Token", "no-check")
                .multiPart("file", new File("src\\test\\java\\jsondatas\\jiracloud.3.postman.json"))
                .log().all()
                .when()
                .post("/rest/api/3/issue/{issueIdOrKey}/attachments")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }
}
