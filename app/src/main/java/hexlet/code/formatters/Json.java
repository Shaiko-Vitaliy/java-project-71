

package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Json {
    public static String makeJson(TreeMap<String, LinkedHashMap<String, Object>> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return objectMapper.writeValueAsString(map);
    }
}
