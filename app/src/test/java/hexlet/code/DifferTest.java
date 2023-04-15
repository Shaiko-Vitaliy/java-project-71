package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();
    private static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    private static final String SECOND_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/secondFile.json";
    private static final String EXPECTED_STYLISH_PATCH = RESOURCES_PATCH + "/resultStylishFormatExpected.txt";
    private static final String EXPECTED_JSON_PATCH = RESOURCES_PATCH + "/resultJsonFormatExpected.txt";
    private static final String EXPECTED_PLAIN_PATCH = RESOURCES_PATCH + "/resultPlainFormatExpected.txt";
    private static final String FIRST_FILE_PATCH_YAML = RESOURCES_PATCH + "/yaml/firstFile.yml";
    private static final String SECOND_FILE_PATCH_YAML = RESOURCES_PATCH + "/yaml/secondFile.yml";
    private static String expectedStylish;
    private static String expectedJson;
    private static String expectedPlain;


    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = Files.readString(Paths.get(EXPECTED_STYLISH_PATCH));
        expectedJson = Files.readString(Paths.get(EXPECTED_JSON_PATCH));
        expectedPlain = Files.readString(Paths.get(EXPECTED_PLAIN_PATCH));
    }

    @Test
    public void generateInputJsonTest() {
        var actualResult = Differ.generate(FIRST_FILE_PATCH_JSON, SECOND_FILE_PATCH_JSON);
        assertEquals(expectedStylish, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");

    }

    @Test
    public void generateInputYamlTest() {
        String actualResult = Differ.generate(FIRST_FILE_PATCH_YAML, SECOND_FILE_PATCH_YAML, "stylish");
        assertEquals(expectedStylish, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatJsonTest() {
        String actualResult = Differ.generate(FIRST_FILE_PATCH_YAML, SECOND_FILE_PATCH_YAML, "json");
        assertEquals(expectedJson, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatPlainTest() {
        String actualResult = Differ.generate(FIRST_FILE_PATCH_YAML, SECOND_FILE_PATCH_YAML, "plain");
        assertEquals(expectedPlain, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
