package hexlet.code.formatters;

import hexlet.code.Constants;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    @Test
    public void makeJsonTest() throws IOException {
        var actualOutPutJson = Json.format(Constants.TREE_MAP);
        assertEquals(Constants.EXPECTED_JSON, actualOutPutJson, "Json.makeJson() "
                + "не получилось вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }
}
