package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String lineFromFile, String formatInputFile) throws Exception {
        Map<String, Object> map;
        switch (formatInputFile) {
            case "json" -> {
                map = parseJson(lineFromFile);
                return map;
            }
            case "yml", "yaml" -> {
                map = parseYaml(lineFromFile);
                return map;
            }
            default -> throw new IllegalArgumentException("Format file not valid");
        }
    }

    private static Map<String, Object> parseJson(String line) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(line, new TypeReference<>() {
        });
    }

    private static Map<String, Object> parseYaml(String line) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(line, HashMap.class);
    }
}
