package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.HashMap;
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
        ObjectMapper objectMapper = new JsonMapper();
        Map<String, Object> result = new HashMap<>();
        try {
            result = objectMapper.readValue(fileContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    private static Map<String, Object> parseYaml(String fileContent) {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> result = new HashMap<>();
        try {
            result = mapper.readValue(fileContent, new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return result;
    }
}
