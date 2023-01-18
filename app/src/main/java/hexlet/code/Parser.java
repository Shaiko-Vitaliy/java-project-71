package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.HashMap;
import java.util.Map;

public class Parser {



    public static Map<String, Object> parsingFile(String filePatch) throws Exception {
        Map<String, Object> map = new HashMap<>();
        var lineFromFile = Utils.readingFileJson(filePatch);
        String formatInputFile = Utils.givesFormatInputFile(filePatch);
        switch (formatInputFile) {
            case "json" -> {
                map = makesFromJsonToMap(lineFromFile);
                return map;
            }
            case "yml" -> {
                map = makesFromYamlToMap(lineFromFile);
                return map;
            }
            default -> {
                return new HashMap<>();
            }
        }


    }

    private static Map<String, Object> makesFromJsonToMap(String line) throws Exception {
        Map<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        map = objectMapper.readValue(line, new TypeReference<>() {
        });
        return map;
    }

    private static Map<String, Object> makesFromYamlToMap(String line) throws Exception {
        Map<String, Object> map;
        ObjectMapper mapper = new YAMLMapper();
        map = mapper.readValue(line, HashMap.class);
        return map;
    }
}
