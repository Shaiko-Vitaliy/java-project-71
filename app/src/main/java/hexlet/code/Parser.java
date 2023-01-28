package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parsingFile(String lineFromFile, String formatInputFile) throws Exception {
        Map<String, Object> map;
        switch (formatInputFile) {
            case "json" -> {
                map = makesFromJsonToMap(lineFromFile);
                return map;
            }
            case "yml", "yaml" -> {
                map = makesFromYamlToMap(lineFromFile);
                return map;
            }
            default -> throw new IllegalArgumentException("Format file not valid");
        }
    }

    private static Map<String, Object> makesFromJsonToMap(String line) throws JsonProcessingException {
        Map<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        map = objectMapper.readValue(line, new TypeReference<>() {
        });
        return map;
    }

    private static Map<String, Object> makesFromYamlToMap(String line) throws JsonProcessingException {
        Map<String, Object> map;
        ObjectMapper mapper = new YAMLMapper();
        map = mapper.readValue(line, HashMap.class);
        return map;
    }
}
