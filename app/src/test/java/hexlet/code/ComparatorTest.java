package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {
    private static String expectedResultCompare;

    @BeforeAll
    public static void beforeAll() throws Exception {
        var lineFromFilePatch = Constants.RESOURCES_PATCH + "/resultComparatorExpected.txt";
        expectedResultCompare = Files.readString(Paths.get(lineFromFilePatch));
    }
    @Test
    public void findDiffTest() {
        var actualCompareMaps = Comparator.findDiff(Constants.FIRST_MAP, Constants.SECOND_MAP);
        var scan = new Scanner(expectedResultCompare);
        for (Map.Entry<String, LinkedHashMap<String, Object>> item : actualCompareMaps.entrySet()) {
            assertEquals(scan.nextLine(), item.getKey() + "=" + item.getValue(),
                    "Comparator.findDiff() вызвал ошибку"
                           + "или результат не совпал с ожидаемым");
        }
    }
}
