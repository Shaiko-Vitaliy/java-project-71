package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Json {
    public static String format(TreeMap<String, LinkedHashMap<String, Object>> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        var result = "";
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            result = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.out.println(e.toString());
        }
        return result;
    }
}
