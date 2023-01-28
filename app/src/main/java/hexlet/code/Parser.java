package hexlet.code;

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

    private static Map<String, Object> makesFromJsonToMap(String line) throws Exception {
        Map<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(line, new TypeReference<>() {
            });
            return map;
        } catch (Exception e) {
            throw new Exception("File is empty");
        }
    }

    private static Map<String, Object> makesFromYamlToMap(String line) throws Exception {
        Map<String, Object> map;
        ObjectMapper mapper = new YAMLMapper();
        try {
            map = mapper.readValue(line, HashMap.class);
            return map;
        } catch (Exception e) {
            throw new Exception("File is empty");
        }
    }
}
