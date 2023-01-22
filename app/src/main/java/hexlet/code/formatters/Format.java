package hexlet.code.formatters;

import java.util.HashMap;
import java.util.Map;

public class Format {
    public static String getResultInOutFormat(Map<String, HashMap<String, Object>> map, String format) {
        switch (format) {
            case "stylish" -> {
                return Stylish.makeFromStylish(map);
            }
            default -> {
                return "Введен не верный формат выходных данных";
            }
        }
    }
}
