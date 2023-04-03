package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static Map<String, Object> expectedMapFromFirstFile = new HashMap<>();

    @BeforeAll
    public static void beforeAll() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        expectedMapFromFirstFile = objectMapper.readValue(FilePaths.LINE_FROM_FIRST_FILE_JSON, new TypeReference<>() {
        });
    }
    @Test
    public void parseTest() throws Exception {
        Map<String, Object> actualResultJson = Parser.parse(FilePaths.LINE_FROM_FIRST_FILE_JSON, "json");
        Map<String, Object> actualResultYaml = Parser.parse(FilePaths.LINE_FROM_FIRST_FILE_YAML, "yml");
        assertEquals(expectedMapFromFirstFile, actualResultJson, "Parse.parsingFile() не получилось спарсить"
                + "строку формата JSON и получить МАР или результат не совпал с ожидаемым");
        assertEquals(expectedMapFromFirstFile, actualResultYaml, "Parse.parsingFile() не получилось спарсить"
                + "строку формата YAML и получить МАР или результат не совпал с ожидаемым");
    }
}
