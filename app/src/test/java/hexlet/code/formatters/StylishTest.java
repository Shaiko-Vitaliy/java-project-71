package hexlet.code.formatters;

import hexlet.code.Comparator;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTest {
    private static Map<String, HashMap<String, Object>> map;
    private static String expectedStylishTxt;
    private static final TreeMap<String, Object> TREE_MAP = new TreeMap<>();

    @BeforeAll
    private static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        var expectedStylishFormatFilePatchTxt = resourcesPatch + "/resultStylishFormatExpected.txt";
        var firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        var secondFilePatchJson = resourcesPatch + "/json/secondFile.json";

        var lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        var lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedStylishTxt = Files.readString(Paths.get(expectedStylishFormatFilePatchTxt));

        Map<String, Object> map1 = Parser.parsingFile(lineFromFirstFileJson, "json");
        Map<String, Object> map2 = Parser.parsingFile(lineFromSecondFileJson, "json");
        TREE_MAP.putAll(map1);
        TREE_MAP.putAll(map2);
        map = Comparator.comparesTwoMaps(map1, map2, TREE_MAP);
    }

    @Test
    public void makeFromStylishTest() {
        var actualOutPutStylish = Stylish.makeFromStylish(map);
        assertEquals(expectedStylishTxt, actualOutPutStylish, "Stylish.makeFromStylish() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }
}
