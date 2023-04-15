package hexlet.code.formatters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Parser;
import hexlet.code.TreeComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FormatTest {
    private static final String FORMAT_STYLISH = "stylish";
    private static final String FORMAT_PLAIN = "plain";
    private static final String FORMAT_JSON = "json";
    private static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();
    private static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    private static final String SECOND_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/secondFile.json";
    private static final String EXPECTED_STYLISH_PATCH = RESOURCES_PATCH + "/resultStylishFormatExpected.txt";
    public static final String EXPECTED_JSON_PATCH = RESOURCES_PATCH + "/resultJsonFormatExpected.txt";
    private static final String EXPECTED_PLAIN_PATCH = RESOURCES_PATCH + "/resultPlainFormatExpected.txt";
    private static TreeMap<String, LinkedHashMap<String, Object>> treeMap;
    private static String expectedStylish;
    private static String expectedJson;
    private static String expectedPlain;

    @BeforeAll
    public static void beforeAll() throws Exception {
        String lineFromFirstFileJson = Files.readString(Paths.get(FIRST_FILE_PATCH_JSON));
        String lineFromSecondFileJson = Files.readString(Paths.get(SECOND_FILE_PATCH_JSON));
        Map<String, Object> firstMap = Parser.parse(lineFromFirstFileJson, "json");
        Map<String, Object> secondMap = Parser.parse(lineFromSecondFileJson, "json");
        treeMap = TreeComparator.findDiff(firstMap, secondMap);
        expectedStylish = Files.readString(Paths.get(EXPECTED_STYLISH_PATCH));
        expectedJson = Files.readString(Paths.get(EXPECTED_JSON_PATCH));
        expectedPlain = Files.readString(Paths.get(EXPECTED_PLAIN_PATCH));
    }

    @Test
    public void formatTest() {
        var actualOutPutStylish = Formatter.format(treeMap, FORMAT_STYLISH);
        var actualOutPutPlain = Formatter.format(treeMap, FORMAT_PLAIN);
        var actualOutPutJson = Formatter.format(treeMap, FORMAT_JSON);
        assertEquals(expectedStylish, actualOutPutStylish, "Formatter.format() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
        assertEquals(expectedPlain, actualOutPutPlain, "Formatter.format() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
        assertEquals(expectedJson, actualOutPutJson, "Formatter.format() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}
