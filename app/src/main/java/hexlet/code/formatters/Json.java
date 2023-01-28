package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Json {
    public static String makeFromJson(Map<String, HashMap<String, Object>> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            return objectMapper.writeValueAsString(map);
        } catch (IOException e) {
            throw new IOException("Error the write data to string json");
        }
    }
}
