package hexlet.code.formatters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Plain {
    public static String makeFromPlain(TreeMap<String, HashMap<String, Object>> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, HashMap<String, Object>> item : map.entrySet()) {
            for (Map.Entry<String, Object> key : item.getValue().entrySet()) {
                var valueOfKey = checksCompositeData(key.getValue()).toString();
                switch (key.getKey()) {
                    case "added" -> {
                        builder.append("Property '").append(item.getKey()).append("' was added with value: ")
                                .append(valueOfKey).append("\n");
                    }
                    case "delete" -> {
                        builder.append("Property '").append(item.getKey()).append("' was removed").append("\n");
                    }
                    case "Old value" -> {
                        builder.append("Property '").append(item.getKey()).append("' was updated. From ")
                                .append(checksCompositeData(item.getValue().get("Old value"))).append(" to ")
                                .append(checksCompositeData(item.getValue().get("New value"))).append("\n");
                    }
                    default -> {
                    }
                }
            }
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }

    private static Object checksCompositeData(Object data) throws NullPointerException {
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
