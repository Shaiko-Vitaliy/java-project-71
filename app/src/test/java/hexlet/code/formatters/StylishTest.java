package hexlet.code.formatters;

import hexlet.code.FilePaths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTest {

    @Test
    public void makeStylishTest() {
        var actualOutPutStylish = Stylish.format(FilePaths.TREE_MAP);
        assertEquals(FilePaths.EXPECTED_STYLISH, actualOutPutStylish, "Stylish.makeStylish() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }
}
