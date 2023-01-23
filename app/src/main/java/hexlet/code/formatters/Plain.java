package hexlet.code.formatters;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Plain {
    public static String makeFromPlain(Map<String, HashMap<String, Object>> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, HashMap<String, Object>> item : map.entrySet()) {
            for (Map.Entry<String, Object> key : item.getValue().entrySet()) {
                var valueOfKey = checksForCompositeData(key.getValue()).toString();
                switch (key.getKey()) {
                    case "added" -> {
                        builder.append("Property '").append(item.getKey()).append("' was added with value: ")
                                .append(valueOfKey).append("\n");
                    }
                    case "delet" -> {
                        builder.append("Property '").append(item.getKey()).append("' was removed").append("\n");
                    }
                    case "Old modified" -> {
                        builder.append("Property '").append(item.getKey()).append("' was updated. From ")
                                .append(checksForCompositeData(item.getValue().get("Old modified"))).append(" to ")
                                .append(checksForCompositeData(item.getValue().get("New modified"))).append("\n");
                    }
                    default -> {
                    }
                }
            }
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }

    private static Object checksForCompositeData(Object data) throws NullPointerException {
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

