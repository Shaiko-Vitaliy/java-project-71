package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String firstFilePatchJson;
    private static String secondFilePatchJson;
    private static String firstFilePatchYaml;
    private static String secondFilePatchYaml;
    private static String expectedFileComprasion;
    private static Map<String, Object> expectedMapFromFirstFile;
    @BeforeAll
    public static void beforeAll() {
        String resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        secondFilePatchJson = resourcesPatch + "/json/secondFile.json";
        firstFilePatchYaml = resourcesPatch + "/yaml/firstFile.yml";
        secondFilePatchYaml = resourcesPatch + "/yaml/secondFile.yml";
        expectedFileComprasion = """
                {
                    date: 1
                  - follow: false
                  - host: shaiko.com
                  + host: hexlet.io
                  + id: 456
                  - name: Cris
                  + name: Stenli
                  - proxy: 123.234.53.22
                  - timeout: 0
                  + timeout: 1
                  + verbose: true
                }""";
        expectedMapFromFirstFile = Map.of("host", "shaiko.com", "timeout", 0,
                "proxy", "123.234.53.22", "follow", false,
                "name", "Cris", "date", 1);

    }
    @Test
    public void generateFormJsonTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchJson, secondFilePatchJson, "format");
        assertEquals(expectedFileComprasion, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в текстовом виде или результат не совпал с ожидаемым");
    }

    @Test
    public void generateFormYamlTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchYaml, secondFilePatchYaml, "format");
        assertEquals(expectedFileComprasion, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в текстовом виде или результат не совпал с ожидаемым");
    }

    @Test
    public void parsingFileTest() throws Exception {
        Map<String, Object> actualResult = Parser.parsingFile(firstFilePatchJson);
        assertEquals(expectedMapFromFirstFile, actualResult, "Parser.parsingFile() не удалось спарсить файл "
                + "или ожидаемая МАР не совпала с результатом");
    }
}
