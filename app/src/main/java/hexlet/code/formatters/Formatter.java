package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Formatter {
    public static String format(TreeMap<String, LinkedHashMap<String, Object>> map, String format) {
        switch (format) {
            case "stylish" -> {
                return Stylish.format(map);
            }
            case "plain" -> {
                return Plain.format(map);
            }
            case "json" -> {
                return Json.format(map);
            }
            default -> {
                throw new IllegalArgumentException("Out format not valid");
            }
        }
    }
}
