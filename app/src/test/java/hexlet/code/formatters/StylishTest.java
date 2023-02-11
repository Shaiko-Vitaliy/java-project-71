package hexlet.code.formatters;

import hexlet.code.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishTest {

    @Test
    public void makeStylishTest() {
        var actualOutPutStylish = Stylish.makeStylish(Constants.TREE_MAP);
        assertEquals(Constants.EXPECTED_STYLISH, actualOutPutStylish, "Stylish.makeStylish() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }
}
