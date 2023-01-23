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

public class FormatTest {
    private static Map<String, HashMap<String, Object>> map;
    private static String expectedStylishTxt;
    private static String expectedPlainTxt;
    private final static TreeMap<String, Object> treeMap = new TreeMap<>();

    @BeforeAll
    public static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        var expectedStylishFormatFilePatchTxt = resourcesPatch + "/resultStylishFormatExpected.txt";
        var expectedPlainTxtFormatFilePatchTxt = resourcesPatch + "/resultPlainFormatExpected.txt";
        var firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        var secondFilePatchJson = resourcesPatch + "/json/secondFile.json";

        var lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        var lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedStylishTxt = Files.readString(Paths.get(expectedStylishFormatFilePatchTxt));
        expectedPlainTxt = Files.readString(Paths.get(expectedPlainTxtFormatFilePatchTxt));

        var map1 = Parser.parsingFile(lineFromFirstFileJson, "json");
        var map2 = Parser.parsingFile(lineFromSecondFileJson, "json");
        treeMap.putAll(map1);
        treeMap.putAll(map2);
        map = Comparator.comparesTwoMaps(map1, map2, treeMap);
    }

    @Test
    public void getResultInOutFormatTest() {
        String formatStylish = "stylish";
        String formatPlain = "plain";
        var actualOutPutStylish = Format.getResultInOutFormat(map, formatStylish);
        var actualOutPutPlain = Format.getResultInOutFormat(map, formatPlain);
        assertEquals(expectedStylishTxt, actualOutPutStylish, "Format.getResultInOutFormat() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
        assertEquals(expectedPlainTxt, actualOutPutPlain, "Format.getResultInOutFormat() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
