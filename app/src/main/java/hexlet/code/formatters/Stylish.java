package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Stylish {
    public static String format(TreeMap<String, LinkedHashMap<String, Object>> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (Map.Entry<String, LinkedHashMap<String, Object>> item : map.entrySet()) {
            var command = item.getValue().get("type").toString();
            switch (command) {
                case "added" -> {
                    var value = item.getValue().get("value");
                    builder.append("  ").append("+ ").append(item.getKey()).append(": ")
                            .append(value.toString()).append("\n");
                }
                case "deleted" -> {
                    var value = item.getValue().get("value");
                    builder.append("  ").append("- ").append(item.getKey()).append(": ")
                            .append(value.toString()).append("\n");
                }
                case "unchanged" -> {
                    var value = item.getValue().get("value");
                    builder.append("  ").append("  ").append(item.getKey()).append(": ")
                            .append(value.toString()).append("\n");
                }
                case "changed" -> {
                    var value1 = item.getValue().get("value1");
                    var value2 = item.getValue().get("value2");
                    builder.append("  ").append("- ").append(item.getKey()).append(": ")
                            .append(value1).append("\n");
                    builder.append("  ").append("+ ").append(item.getKey()).append(": ")
                            .append(value2).append("\n");
                }
                case "New value" -> {
                }
                default -> {
                    throw new IllegalArgumentException("Illegal modifier");
                }
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
