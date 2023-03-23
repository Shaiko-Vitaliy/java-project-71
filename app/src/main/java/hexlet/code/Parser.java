package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String fileContent, String format) throws IOException {
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

    private static Map parseJson(String fileContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileContent, Map.class);
    }

    private static Map parseYaml(String fileContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(fileContent, Map.class);
    }
}
