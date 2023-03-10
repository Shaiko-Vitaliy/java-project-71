package hexlet.code.formatters;

import hexlet.code.Constants;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FormatTest {
    @Test
    public void formatTest() throws IOException {
        var actualOutPutStylish = Formatter.format(Constants.TREE_MAP, Constants.FORMAT_STYLISH);
        var actualOutPutPlain = Formatter.format(Constants.TREE_MAP, Constants.FORMAT_PLAIN);
        var actualOutPutJson = Formatter.format(Constants.TREE_MAP, Constants.FORMAT_JSON);
        assertEquals(Constants.EXPECTED_STYLISH, actualOutPutStylish, "Format.format() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
        assertEquals(Constants.EXPECTED_PLAIN, actualOutPutPlain, "Format.format() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
        assertEquals(Constants.EXPECTED_JSON, actualOutPutJson, "Format.format() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}
