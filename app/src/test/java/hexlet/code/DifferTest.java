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
    private static String stylishFormatExpected;
    private static String jsonFormatExpected;
    private static String plainFormatExpected;
    @BeforeAll
    public static void beforeAll() throws IOException {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        secondFilePatchJson = resourcesPatch + "/json/secondFile.json";
        firstFilePatchYaml = resourcesPatch + "/yaml/firstFile.yml";
        secondFilePatchYaml = resourcesPatch + "/yaml/secondFile.yml";
        var expectedStylishFormatFilePatchTxt = resourcesPatch + "/resultStylishFormatExpected.txt";
        var expectedJsonFormatFilePatchTxt = resourcesPatch + "/resultJsonFormatExpected.txt";
        var expectedPlainFormatFilePatchTxt = resourcesPatch + "/resultPlainFormatExpected.txt";
        stylishFormatExpected = Files.readString(Paths.get(expectedStylishFormatFilePatchTxt));
        jsonFormatExpected = Files.readString(Paths.get(expectedJsonFormatFilePatchTxt));
        plainFormatExpected = Files.readString(Paths.get(expectedPlainFormatFilePatchTxt));
    }

    @Test
    public void generateFromJsonTest() throws Exception {
        var actualResult = Differ.generate(firstFilePatchJson, secondFilePatchJson);
        assertEquals(stylishFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                 + " два файла JSON и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");

    }

    @Test
    public void generateFromYamlTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchYaml, secondFilePatchYaml, "stylish");
        assertEquals(stylishFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatJsonTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchYaml, secondFilePatchYaml, "json");
        assertEquals(jsonFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatPlainTest() throws Exception {
        String actualResult = Differ.generate(firstFilePatchYaml, secondFilePatchYaml, "plain");
        assertEquals(plainFormatExpected, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
