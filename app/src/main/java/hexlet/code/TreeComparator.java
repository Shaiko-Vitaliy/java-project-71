package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TreeComparator {
    private static final  String ADDED = "added";
    private static final  String DELETED = "deleted";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";
    public static TreeMap<String, LinkedHashMap<String, Object>> findDiff(Map<String, Object> map1,
                                                                          Map<String, Object> map2) {
        TreeMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(map1);
        sortedMap.putAll(map2);
        TreeMap<String, LinkedHashMap<String, Object>> result = new TreeMap<>();
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            LinkedHashMap<String, Object> value = new LinkedHashMap<>();
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey())) {
                value.put("type", DELETED);
                value.put("value", valueMap1);
                result.put(item.getKey(), value);
            } else if (!map1.containsKey(item.getKey())) {
                value.put("type", ADDED);
                value.put("value", valueMap2);
                result.put(item.getKey(), value);
            } else {
                if (Objects.equals(valueMap1, valueMap2)) {
                    value.put("type", UNCHANGED);
                    value.put("value", item.getValue());
                    result.put(item.getKey(), value);
                } else {
                    value.put("type", CHANGED);
                    value.put("value1", valueMap1);
                    value.put("value2", valueMap2);
                    result.put(item.getKey(), value);
                }
            }
        }
        return result;
    }
}
