package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static String firstFilePatchJson;
    private static String firstFilePatchYaml;
    private static Map<String, Object> expectedMapFromFirstFile = new HashMap<>();

    @BeforeAll
    public static void beforeAll() {
        String resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        firstFilePatchYaml = resourcesPatch + "/yaml/firstFile.yml";

        expectedMapFromFirstFile.put("setting1", "Some value");
        expectedMapFromFirstFile.put("setting2", 0);
        expectedMapFromFirstFile.put("setting3", true);
        expectedMapFromFirstFile.put("key1", "value1");
        expectedMapFromFirstFile.put("numbers1", List.of(1, 2, 3, 4));
        expectedMapFromFirstFile.put("numbers2", List.of(2, 3, 4, 5));
        expectedMapFromFirstFile.put("id", 1);
        expectedMapFromFirstFile.put("default", null);
        expectedMapFromFirstFile.put("checked", false);
        expectedMapFromFirstFile.put("numbers3", List.of(3, 4, 5));
        expectedMapFromFirstFile.put("chars1", List.of("a", "b", "c"));
        expectedMapFromFirstFile.put("chars2", List.of("d", "e", "f"));
        expectedMapFromFirstFile.put("obj1", Map.of("nestedKey", "value", "isNested", true));
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
