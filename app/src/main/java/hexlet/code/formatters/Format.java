package hexlet.code.formatters;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class Format {
    public static String format(TreeMap<String, HashMap<String, Object>> map, String format)
            throws IOException {
        switch (format) {
            case "stylish" -> {
                return Stylish.makeFromStylish(map);
            }
            case "plain" -> {
                return Plain.makeFromPlain(map);
            }
            case "json" -> {
                return Json.makeFromJson(map);
            }
            default -> {
                throw new IllegalArgumentException("Out format not valid");
            }
        }
    }
}
