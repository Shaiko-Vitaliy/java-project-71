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
    //private static final Map<String, Object> EXPECTED_MAP_FROM_FIRST_FILE = new HashMap<>();
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
/*
        EXPECTED_MAP_FROM_FIRST_FILE.put("setting1", "Some value");
        EXPECTED_MAP_FROM_FIRST_FILE.put("setting2", 0);
        EXPECTED_MAP_FROM_FIRST_FILE.put("setting3", true);
        EXPECTED_MAP_FROM_FIRST_FILE.put("key1", "value1");
        EXPECTED_MAP_FROM_FIRST_FILE.put("numbers1", List.of(1, 2, 3, 4));
        EXPECTED_MAP_FROM_FIRST_FILE.put("numbers2", List.of(2, 3, 4, 5));
        EXPECTED_MAP_FROM_FIRST_FILE.put("id", 1);
        EXPECTED_MAP_FROM_FIRST_FILE.put("default", null);
        EXPECTED_MAP_FROM_FIRST_FILE.put("checked", false);
        EXPECTED_MAP_FROM_FIRST_FILE.put("numbers3", List.of(3, 4, 5));
        EXPECTED_MAP_FROM_FIRST_FILE.put("chars1", List.of("a", "b", "c"));
        EXPECTED_MAP_FROM_FIRST_FILE.put("chars2", List.of("d", "e", "f"));
        EXPECTED_MAP_FROM_FIRST_FILE.put("obj1", Map.of("nestedKey", "value", "isNested", true));

 */



    }
    @Test
    public void parsingFileTest() throws Exception {
        var lineFromFirstFile = Files.readString(Paths.get(firstFilePatchJson));
        Map<String, Object> actualResultJson = Parser.parsingFile(lineFromFirstFile, "json");
        var lineFromFirstFileYaml = Files.readString(Paths.get(firstFilePatchYaml));
        Map<String, Object> actualResultYaml = Parser.parsingFile(lineFromFirstFileYaml, "yml");
        assertEquals(expectedMapFromFirstFile, actualResultJson, "Parse.parsingFile() не получилось спарсить"
                + "строку формата JSON и получить МАР или результат не совпал с ожидаемым");
        assertEquals(expectedMapFromFirstFile, actualResultYaml, "Parse.parsingFile() не получилось спарсить"
                + "строку формата YAML и получить МАР или результат не совпал с ожидаемым");
    }


}
