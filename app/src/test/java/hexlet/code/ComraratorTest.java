package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComraratorTest {
    private static TreeMap<String, Object> treeMap = new TreeMap<>();
    private static String expectedLineFromFilePatchTxt;
    private static Map<String, Object> map1;
    private static Map<String, Object> map2;

    @BeforeAll
    public static void beforeAll() throws Exception {
        String resourcesPatch = new File("src/test/resources").getAbsolutePath();
        String firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        String secondFilePatchJson = resourcesPatch + "/json/secondFile.json";
        String expectedFilePatchTxt = resourcesPatch + "/resultComparatorExpected.txt";

        String lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        String lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedLineFromFilePatchTxt = Files.readString(Paths.get(expectedFilePatchTxt));

        map1 = Parser.parsingFile(lineFromFirstFileJson, "json");
        map2 = Parser.parsingFile(lineFromSecondFileJson, "json");
        treeMap.putAll(map1);
        treeMap.putAll(map2);
    }
    @Test
    public void comparesTwoMapsTest() {
        var actualCompareMaps = Comparator.comparesTwoMaps(map1, map2, treeMap);
        Scanner scan = new Scanner(expectedLineFromFilePatchTxt);
        for (Map.Entry<String, HashMap<String, Object>> item : actualCompareMaps.entrySet()) {
            assertEquals(item.getKey() + item.getValue(), scan.nextLine(),
                    "Comparator.comparesTwoMaps() вызвал ошибку"
                    + "или результат не совпал с ожидаемым");
        }
    }
}
