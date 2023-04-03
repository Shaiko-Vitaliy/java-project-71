package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parse(String fileContent, String format) {
        Map<String, Object> map;
        switch (format) {
            case "json" -> {
                map = parseJson(fileContent);
                return map;
            }
            case "yml", "yaml" -> {
                map = parseYaml(fileContent);
                return map;
            }
            default -> throw new IllegalArgumentException("Format file not valid");
        }
    }

    private static Map<String, Object> parseJson(String fileContent) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> result;
        try {
            result = objectMapper.readValue(fileContent, Map.class);
        } catch (IOException e) {
            System.err.println(e.toString());
            throw new RuntimeException(e.toString());
        }
        return result;
    }

    private static Map parseYaml(String fileContent) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> result;
        try {
            result = objectMapper.readValue(fileContent, Map.class);
        } catch (IOException e) {
            System.err.println(e.toString());
            throw new RuntimeException(e.toString());
        }
        return result;
    }
}
