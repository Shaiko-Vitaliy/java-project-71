package hexlet.code.formatters;

import hexlet.code.FilePaths;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FormatTest {
    @Test
    public void formatTest() throws IOException {
        var actualOutPutStylish = Formatter.format(FilePaths.TREE_MAP, FilePaths.FORMAT_STYLISH);
        var actualOutPutPlain = Formatter.format(FilePaths.TREE_MAP, FilePaths.FORMAT_PLAIN);
        var actualOutPutJson = Formatter.format(FilePaths.TREE_MAP, FilePaths.FORMAT_JSON);
        assertEquals(FilePaths.EXPECTED_STYLISH, actualOutPutStylish, "Format.format() "
                + "не получилось вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
        assertEquals(FilePaths.EXPECTED_PLAIN, actualOutPutPlain, "Format.format() "
                + "не получилось вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
        assertEquals(FilePaths.EXPECTED_JSON, actualOutPutJson, "Format.format() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}
