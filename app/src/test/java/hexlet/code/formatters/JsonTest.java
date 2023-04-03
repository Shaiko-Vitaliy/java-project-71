package hexlet.code.formatters;

import hexlet.code.FilePaths;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    @Test
    public void makeJsonTest() throws IOException {
        var actualOutPutJson = Json.format(FilePaths.TREE_MAP);
        assertEquals(FilePaths.EXPECTED_JSON, actualOutPutJson, "Json.makeJson() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}
