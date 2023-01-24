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
    private static final TreeMap<String, Object> TREE_MAP = new TreeMap<>();
    private static String expectedLineFromFilePatchTxt;
    private static Map<String, Object> map1;
    private static Map<String, Object> map2;

    @BeforeAll
    public static void beforeAll() throws Exception {
        var resourcesPatch = new File("src/test/resources").getAbsolutePath();
        var firstFilePatchJson = resourcesPatch + "/json/firstFile.json";
        var secondFilePatchJson = resourcesPatch + "/json/secondFile.json";
        var expectedFilePatchTxt = resourcesPatch + "/resultComparatorExpected.txt";

        var lineFromFirstFileJson = Files.readString(Paths.get(firstFilePatchJson));
        var lineFromSecondFileJson = Files.readString(Paths.get(secondFilePatchJson));
        expectedLineFromFilePatchTxt = Files.readString(Paths.get(expectedFilePatchTxt));

        map1 = Parser.parsingFile(lineFromFirstFileJson, "json");
        map2 = Parser.parsingFile(lineFromSecondFileJson, "json");
        TREE_MAP.putAll(map1);
        TREE_MAP.putAll(map2);
    }
    @Test
    public void comparesTwoMapsTest() {
        var actualCompareMaps = Comparator.comparesTwoMaps(map1, map2, TREE_MAP);
        var scan = new Scanner(expectedLineFromFilePatchTxt);
        for (Map.Entry<String, HashMap<String, Object>> item : actualCompareMaps.entrySet()) {
            assertEquals(scan.nextLine(), item.getKey() + item.getValue(),
                    "Comparator.comparesTwoMaps() вызвал ошибку"
                    + "или результат не совпал с ожидаемым");
        }
    }
}
