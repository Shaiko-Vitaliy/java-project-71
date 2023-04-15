package hexlet.code.formatters;

import hexlet.code.Parser;
import hexlet.code.TreeComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTest {

    private static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();
    private static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    private static final String SECOND_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/secondFile.json";
    private static final String EXPECTED_STYLISH_PATCH = RESOURCES_PATCH + "/resultStylishFormatExpected.txt";
    private static TreeMap<String, LinkedHashMap<String, Object>> treeMap;
    private static String expectedStylish;

    @BeforeAll
    public static void beforeAll() throws Exception {
        String lineFromFirstFileJson = Files.readString(Paths.get(FIRST_FILE_PATCH_JSON));
        String lineFromSecondFileJson = Files.readString(Paths.get(SECOND_FILE_PATCH_JSON));
        Map<String, Object> firstMap = Parser.parse(lineFromFirstFileJson, "json");
        Map<String, Object> secondMap = Parser.parse(lineFromSecondFileJson, "json");
        treeMap = TreeComparator.findDiff(firstMap, secondMap);
        expectedStylish = Files.readString(Paths.get(EXPECTED_STYLISH_PATCH));
    }

    @Test
    public void makeStylishTest() {
        var actualOutPutStylish = Stylish.format(treeMap);
        assertEquals(expectedStylish, actualOutPutStylish, "Stylish.format() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }
}
