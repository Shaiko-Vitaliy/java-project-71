package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class FilePaths {
    public static final String FORMAT_STYLISH = "stylish";
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_JSON = "json";

    public static final String RESOURCES_PATCH = new File("src/test/resources").getAbsolutePath();

    public static final String FIRST_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/firstFile.json";
    public static final String SECOND_FILE_PATCH_JSON = RESOURCES_PATCH + "/json/secondFile.json";
    public static final String FIRST_FILE_PATCH_YAML = RESOURCES_PATCH + "/yaml/firstFile.yml";
    public static final String SECOND_FILE_PATCH_YAML = RESOURCES_PATCH + "/yaml/secondFile.yml";
    public static final String EXPECTED_STYLISH_PATCH = RESOURCES_PATCH + "/resultStylishFormatExpected.txt";
    public static final String EXPECTED_PLAIN_PATCH = RESOURCES_PATCH + "/resultPlainFormatExpected.txt";
    public static final String EXPECTED_JSON_PATCH = RESOURCES_PATCH + "/resultJsonFormatExpected.txt";
    public static final String LINE_FROM_FIRST_FILE_JSON;
    public static final String LINE_FROM_FIRST_FILE_YAML;
    public static final String LINE_FROM_SECOND_FILE_JSON;
    public static final String EXPECTED_STYLISH;
    public static final String EXPECTED_PLAIN;
    public static final String EXPECTED_JSON;
    public static final TreeMap<String, LinkedHashMap<String, Object>> TREE_MAP;
    public static final Map<String, Object> FIRST_MAP;
    public static final Map<String, Object> SECOND_MAP;

    static {
        try {
            LINE_FROM_FIRST_FILE_JSON = Files.readString(Paths.get(FIRST_FILE_PATCH_JSON));
            LINE_FROM_SECOND_FILE_JSON = Files.readString(Paths.get(SECOND_FILE_PATCH_JSON));
            LINE_FROM_FIRST_FILE_YAML = Files.readString(Paths.get(FIRST_FILE_PATCH_YAML));
            EXPECTED_STYLISH = Files.readString(Paths.get(EXPECTED_STYLISH_PATCH));
            EXPECTED_PLAIN = Files.readString(Paths.get(EXPECTED_PLAIN_PATCH));
            EXPECTED_JSON = Files.readString(Paths.get(EXPECTED_JSON_PATCH));
            FIRST_MAP = Parser.parse(FilePaths.LINE_FROM_FIRST_FILE_JSON, "json");
            SECOND_MAP = Parser.parse(FilePaths.LINE_FROM_SECOND_FILE_JSON, "json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TREE_MAP = TreeComparator.findDiff(FIRST_MAP, SECOND_MAP);
    }
}
