package hexlet.code.formatters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Format {
    public static String getResultInOutFormat(Map<String, HashMap<String, Object>> map, String format)
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
                return "Введен не верный формат выходных данных";
            }
        }
    }
}
