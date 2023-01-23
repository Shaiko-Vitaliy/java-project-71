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

public class PlainTest {
    private static Map<String, HashMap<String, Object>> map;
    private static String expectedPlainTxt;
    private static TreeMap<String, Object> treeMap = new TreeMap<>();

    @BeforeAll
    private static void beforeAll() throws Exception {
        String resourcesPatch = new File("src/test/resources").getAbsolutePath();
        String expectedPlainTxtFormatFilePatchTxt = resourcesPatch + "/resultPlainFormatExpected.txt";
        String firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        String secondFilePatchJson = resourcesPatch + "/json/secondFile.json";

        String lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        String lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedPlainTxt = Files.readString(Paths.get(expectedPlainTxtFormatFilePatchTxt));

        Map<String, Object> map1 = Parser.parsingFile(lineFromFirstFileJson, "json");
        Map<String, Object> map2 = Parser.parsingFile(lineFromSecondFileJson, "json");
        treeMap.putAll(map1);
        treeMap.putAll(map2);
        map = Comparator.comparesTwoMaps(map1, map2, treeMap);
    }

    @Test
    public void makeFromStylishTest() {
        var actualOutPutPlain = Plain.makeFromPlain(map);
        assertEquals(expectedPlainTxt, actualOutPutPlain, "Plain.makeFromPlain() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
