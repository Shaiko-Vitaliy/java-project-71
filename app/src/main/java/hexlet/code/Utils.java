package hexlet.code;

import java.util.Map;
import java.util.TreeMap;

public class Utils {

    public static String comparesTwoMaps(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder builder = new StringBuilder();
        TreeMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(map1);
        sortedMap.putAll(map2);
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey()) && map1.containsKey(item.getKey())) {
                builder.append("- " + item.getKey() + ":" + " " + item.getValue() + "\n");
            }
            else if (!map1.containsKey(item.getKey()) && map2.containsKey(item.getKey())) {
                builder.append("+ " + item.getKey() + ":" + " " + item.getValue() + "\n");
            } else {
                if (valueMap2 == null || valueMap1 == null) {
                    if (valueMap2 != valueMap1) {
                        builder.append("- " + item.getKey() + ":" + " " + valueMap1 + "\n");
                        builder.append("+ " + item.getKey() + ":" + " " + valueMap2 + "\n");
                    } else {
                        builder.append("  " + item.getKey() + ":" + " " + item.getValue() + "\n");
                    }
                } else {
                    if (!valueMap2.equals(valueMap1)) {
                        builder.append("- " + item.getKey() + ":" + " " + valueMap1 + "\n");
                        builder.append("+ " + item.getKey() + ":" + " " + valueMap2 + "\n");
                    } else {
                        builder.append("  " + item.getKey() + ":" + " " + item.getValue() + "\n");
                    }
                }
            }
        }
        return builder.toString();
    }
}

