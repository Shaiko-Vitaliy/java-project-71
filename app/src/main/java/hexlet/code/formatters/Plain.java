package hexlet.code.formatters;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Plain {
    public static String makePlain(TreeMap<String, LinkedHashMap<String, Object>> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, LinkedHashMap<String, Object>> item : map.entrySet()) {
            var command = item.getValue().get("type").toString();
            switch (command) {
                case "added" -> {
                    var value = item.getValue().get("value");
                    var valueCheckComposite = checkCompositeData(value).toString();
                    builder.append("Property '").append(item.getKey()).append("' was added with value: ")
                            .append(valueCheckComposite).append("\n");
                }
                case "deleted" -> {
                    builder.append("Property '").append(item.getKey()).append("' was removed").append("\n");
                }
                case "changed" -> {
                    var value1 = item.getValue().get("value1");
                    var value2 = item.getValue().get("value2");
                    var valueCheckComposite1 = checkCompositeData(value1).toString();
                    var valueCheckComposite2 = checkCompositeData(value2).toString();
                    builder.append("Property '").append(item.getKey()).append("' was updated. From ")
                            .append(valueCheckComposite1).append(" to ")
                            .append(valueCheckComposite2).append("\n");
                }
                case "unchanged" -> {
                }
                default -> {
                    throw new IllegalArgumentException("Illegal modifier");
                }
            }
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }

    private static Object checkCompositeData(Object data) throws NullPointerException {
        if (data == null) {
            return "null";
        } else if (data instanceof Collection<?> || data instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (data instanceof String) {
            return "'" + data + "'";
        }
        return data;
    }
}
