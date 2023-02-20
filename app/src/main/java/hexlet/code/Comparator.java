package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Comparator {
    private static final  String ADD = "added";
    private static final  String DEL = "deleted";
    private static final String CHANG = "changed";
    private static final String UNCHANG = "unchanged";
    public static TreeMap<String, LinkedHashMap<String, Object>> findDiff(Map<String, Object> map1,
                                                                          Map<String, Object> map2) {
        TreeMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(map1);
        sortedMap.putAll(map2);
        TreeMap<String, LinkedHashMap<String, Object>> resultCompare = new TreeMap<>();
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            LinkedHashMap<String, Object> value = new LinkedHashMap<>();
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey())) {
                value.put("type", DEL);
                value.put("value", valueMap1);
                resultCompare.put(item.getKey(), value);
            } else if (!map1.containsKey(item.getKey())) {
                value.put("type", ADD);
                value.put("value", valueMap2);
                resultCompare.put(item.getKey(), value);
            } else {
                if (Objects.equals(valueMap1, valueMap2)) {
                    value.put("type", UNCHANG);
                    value.put("value", item.getValue());
                    resultCompare.put(item.getKey(), value);
                } else {
                    value.put("type", CHANG);
                    value.put("value1", valueMap1);
                    value.put("value2", valueMap2);
                    resultCompare.put(item.getKey(), value);
                }
            }
        }
        return resultCompare;
    }
}
