package hexlet.code.formatters;

import hexlet.code.Comparator;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class FormatTest {
    private static TreeMap<String, HashMap<String, Object>> map;
    private static String expectedStylishTxt;
    private static String expectedPlainTxt;
    private static String expectedJsonTxt;

    @BeforeAll
    public static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        var expectedStylishFormatFilePatchTxt = resourcesPatch + "/resultStylishFormatExpected.txt";
        var expectedPlainTxtFormatFilePatchTxt = resourcesPatch + "/resultPlainFormatExpected.txt";
        var expectedJsonTxtFormatFilePatchTxt = resourcesPatch + "/resultJsonFormatExpected.txt";

        var firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        var secondFilePatchJson = resourcesPatch + "/json/secondFile.json";

        var lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        var lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedStylishTxt = Files.readString(Paths.get(expectedStylishFormatFilePatchTxt));
        expectedPlainTxt = Files.readString(Paths.get(expectedPlainTxtFormatFilePatchTxt));
        expectedJsonTxt = Files.readString(Paths.get(expectedJsonTxtFormatFilePatchTxt));

        var map1 = Parser.parse(lineFromFirstFileJson, "json");
        var map2 = Parser.parse(lineFromSecondFileJson, "json");
        map = Comparator.compare(map1, map2);
    }

    @Test
    public void formatTest() throws IOException {
        var formatStylish = "stylish";
        var formatPlain = "plain";
        var formatJson = "json";
        var actualOutPutStylish = Format.format(map, formatStylish);
        var actualOutPutPlain = Format.format(map, formatPlain);
        var actualOutPutJson = Format.format(map, formatJson);
        assertEquals(expectedStylishTxt, actualOutPutStylish, "Format.getResultInOutFormat() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
        assertEquals(expectedPlainTxt, actualOutPutPlain, "Format.getResultInOutFormat() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
        assertEquals(expectedJsonTxt, actualOutPutJson, "Format.getResultInOutFormat() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}