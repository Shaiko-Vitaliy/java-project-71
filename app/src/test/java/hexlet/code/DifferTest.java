package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String firstFilePatchJson;
    private static String secondFilePatchJson;
    private static String firstFilePatchYaml;
    private static String secondFilePatchYaml;
    private static String resultStylishFormatExpected;
    @BeforeAll
    public static void beforeAll() throws IOException {
        String resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        secondFilePatchJson = resourcesPatch + "/json/secondFile.json";
        firstFilePatchYaml = resourcesPatch + "/yaml/firstFile.yml";
        secondFilePatchYaml = resourcesPatch + "/yaml/secondFile.yml";
        String expectedStylishFormatFilePatchTxt = resourcesPatch + "/resultStylishFormatExpected.txt";
        resultStylishFormatExpected = Files.readString(Paths.get(expectedStylishFormatFilePatchTxt));
    }

    @Test
    public void generateFormJsonTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchJson, secondFilePatchJson, "stylish");
        assertEquals(resultStylishFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                 + " два файла JSON и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");

    }

    @Test
    public void generateFormYamlTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchYaml, secondFilePatchYaml, "stylish");
        assertEquals(resultStylishFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }
}
