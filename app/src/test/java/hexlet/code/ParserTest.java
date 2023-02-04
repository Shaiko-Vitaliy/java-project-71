package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static String firstFilePatchJson;
    private static String firstFilePatchYaml;
    private static Map<String, Object> expectedMapFromFirstFile = new HashMap<>();

    @BeforeAll
    public static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        firstFilePatchYaml = resourcesPatch + "/yaml/firstFile.yml";
        var lineFromFirstFile = Files.readString(Paths.get(firstFilePatchJson));
        ObjectMapper objectMapper = new ObjectMapper();
        expectedMapFromFirstFile = objectMapper.readValue(lineFromFirstFile, new TypeReference<>() {
        });
    }
    @Test
    public void parseTest() throws Exception {
        var lineFromFirstFile = Files.readString(Paths.get(firstFilePatchJson));
        Map<String, Object> actualResultJson = Parser.parse(lineFromFirstFile, "json");
        var lineFromFirstFileYaml = Files.readString(Paths.get(firstFilePatchYaml));
        Map<String, Object> actualResultYaml = Parser.parse(lineFromFirstFileYaml, "yml");
        assertEquals(expectedMapFromFirstFile, actualResultJson, "Parse.parsingFile() не получилось спарсить"
                + "строку формата JSON и получить МАР или результат не совпал с ожидаемым");
        assertEquals(expectedMapFromFirstFile, actualResultYaml, "Parse.parsingFile() не получилось спарсить"
                + "строку формата YAML и получить МАР или результат не совпал с ожидаемым");
    }
}