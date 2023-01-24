package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Comparator {
    public static Map<String, HashMap<String, Object>> comparesTwoMaps(Map<String, Object> map1,
                                                                       Map<String, Object> map2,
                                                                       TreeMap<String, Object> sortedMap) {
        var add = "added";
        var del = "delete";
        var modOld = "Old value";
        var modNew = "New value";
        var notMod = "not modified";
        Map<String, HashMap<String, Object>> resultCompare = new TreeMap<>();
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey())) {
                resultCompare.put(item.getKey(), getHashMap(del, valueMap1));
            } else if (!map1.containsKey(item.getKey())) {
                resultCompare.put(item.getKey(), getHashMap(add, valueMap2));
            } else {
                if (Objects.equals(valueMap1, valueMap2)) {
                    resultCompare.put(item.getKey(), getHashMap(notMod, item.getValue()));
                } else {
                    resultCompare.put(item.getKey(), getHashMap(modOld, valueMap1, modNew, valueMap2));
                }
            }
        }
        return resultCompare;
    }

    private static HashMap<String, Object> getHashMap(String key, Object val) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(key, val);
        return result;
    }

    private static HashMap<String, Object> getHashMap(String key1, Object val1, String key2, Object val2) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(key1, val1);
        result.put(key2, val2);
        return result;
    }
}

