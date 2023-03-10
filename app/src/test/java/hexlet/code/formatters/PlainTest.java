package hexlet.code.formatters;

import hexlet.code.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainTest {

    @Test
    public void makePlainTest() {
        var actualOutPutPlain = Plain.format(Constants.TREE_MAP);
        assertEquals(Constants.EXPECTED_PLAIN, actualOutPutPlain, "Plain.makePlain() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
