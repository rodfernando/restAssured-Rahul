package utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class JsonFileReader {
    public static String jsonReader(String jsonFilePath) {
        try {
            Path pathFileJson = Paths.get(jsonFilePath);
            byte[] data = Files.readAllBytes(pathFileJson);
            String content = new String(data);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//new String(Files.readAllBytes(Paths.get("C:\\Programação\\Java\\Arquivos da Aula\\JSON")))
