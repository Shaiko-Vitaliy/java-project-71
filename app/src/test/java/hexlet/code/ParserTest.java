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
    private static Map<String, Object> expectedMapFromFirstFile = new HashMap<>();
    private static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();
    private static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    private static final String FIRST_FILE_PATCH_YAML = RESOURCES_PATCH + "/yaml/firstFile.yml";
    private static String lineFromFirstFileJson;
    private static String lineFromFirstFileYaml;

    @BeforeAll
    public static void beforeAll() throws Exception {
        lineFromFirstFileJson = Files.readString(Paths.get(FIRST_FILE_PATCH_JSON));
        lineFromFirstFileYaml = Files.readString(Paths.get(FIRST_FILE_PATCH_YAML));
        ObjectMapper objectMapper = new ObjectMapper();
        expectedMapFromFirstFile = objectMapper.readValue(lineFromFirstFileJson, new TypeReference<>() {
        });
    }

    @Test
    public void parseTest() {
        Map<String, Object> actualResultJson = Parser.parse(lineFromFirstFileJson, "json");
        Map<String, Object> actualResultYaml = Parser.parse(lineFromFirstFileYaml, "yml");
        assertEquals(expectedMapFromFirstFile, actualResultJson, "Parser.parse() не получилось спарсить"
                + "строку формата JSON и получить МАР или результат не совпал с ожидаемым");
        assertEquals(expectedMapFromFirstFile, actualResultYaml, "Parser.parse() не получилось спарсить"
                + "строку формата YAML и получить МАР или результат не совпал с ожидаемым");
    }
}
