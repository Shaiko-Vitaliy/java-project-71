package hexlet.code.formatters;

import java.util.HashMap;
import java.util.Map;

public class Stylish {
    public static String makeFromStylish(Map<String, HashMap<String, Object>> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (Map.Entry<String, HashMap<String, Object>> item : map.entrySet()) {
            for (Map.Entry<String, Object> val : item.getValue().entrySet()) {
                switch (val.getKey()) {
                    case "added" -> {
                        builder.append("  ").append("+ ").append(item.getKey()).append(": ")
                                .append(val.getValue().toString()).append("\n");
                    }
                    case "delete" -> {
                        builder.append("  ").append("- ").append(item.getKey()).append(": ")
                                .append(val.getValue().toString()).append("\n");
                    }
                    case "not modified" -> {
                        builder.append("  ").append("  ").append(item.getKey()).append(": ")
                                .append(val.getValue().toString()).append("\n");
                    }
                    case "Old value" -> {
                        builder.append("  ").append("- ").append(item.getKey()).append(": ")
                                .append(item.getValue().get("Old value")).append("\n");
                        builder.append("  ").append("+ ").append(item.getKey()).append(": ")
                                .append(item.getValue().get("New value")).append("\n");
                    }
                    case "New value" -> {
                    }
                    default -> {
                        builder.append("  ").append("not recognized ").append(item.getKey());
                    }
                }
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
