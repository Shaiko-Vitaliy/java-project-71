package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeComparatorTest {
    private static String expectedResultCompare;
    private static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();
    private static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    private static final String SECOND_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/secondFile.json";
    private static Map<String, Object> firstMap;
    private static Map<String, Object> secondMap;

    @BeforeAll
    public static void beforeAll() throws Exception {
        var lineFromFilePatch = RESOURCES_PATCH + "/resultComparatorExpected.txt";
        expectedResultCompare = Files.readString(Paths.get(lineFromFilePatch));
        String lineFromFirstFileJson = Files.readString(Paths.get(FIRST_FILE_PATCH_JSON));
        String lineFromSecondFileJson = Files.readString(Paths.get(SECOND_FILE_PATCH_JSON));
        firstMap = Parser.parse(lineFromFirstFileJson, "json");
        secondMap = Parser.parse(lineFromSecondFileJson, "json");
    }

    @Test
    public void findDiffTest() {
        var actualCompareMaps = TreeComparator.findDiff(firstMap, secondMap);
        var scan = new Scanner(expectedResultCompare);
        for (Map.Entry<String, LinkedHashMap<String, Object>> item : actualCompareMaps.entrySet()) {
            assertEquals(scan.nextLine(), item.getKey() + "=" + item.getValue(),
                    "TreeComparator.findDiff() вызвал ошибку"
                           + "или результат не совпал с ожидаемым");
        }
    }
}
