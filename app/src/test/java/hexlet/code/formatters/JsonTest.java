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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    private static String expectedJsonTxt;
    private static TreeMap<String, HashMap<String, Object>> map;

    @BeforeAll
    public static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        var expectedPlainTxtFormatFilePatchTxt = resourcesPatch + "/resultJsonFormatExpected.txt";
        var firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        var secondFilePatchJson = resourcesPatch + "/json/secondFile.json";

        var lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        var lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedJsonTxt = Files.readString(Paths.get(expectedPlainTxtFormatFilePatchTxt));

        Map<String, Object> map1 = Parser.parse(lineFromFirstFileJson, "json");
        Map<String, Object> map2 = Parser.parse(lineFromSecondFileJson, "json");
        map = Comparator.compare(map1, map2);
    }
    @Test
    public void makeFromJsonTest() throws IOException {
        var actualOutPutJson = Json.makeFromJson(map);
        assertEquals(expectedJsonTxt, actualOutPutJson, "Plain.makeFromPlain() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
