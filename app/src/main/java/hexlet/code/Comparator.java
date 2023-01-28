package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Comparator {
    private static final  String ADD = "added";
    private static final  String DEL = "delete";
    private static final String MOD_OLD = "Old value";
    private static final String MOD_NEW = "New value";
    private static final String NOT_MOD = "not modified";
    public static Map<String, HashMap<String, Object>> comparesTwoMaps(Map<String, Object> map1,
                                                                       Map<String, Object> map2) {
        TreeMap<String, Object> sortedMap = new TreeMap<>();
        sortedMap.putAll(map1);
        sortedMap.putAll(map2);
        Map<String, HashMap<String, Object>> resultCompare = new TreeMap<>();
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey())) {
                resultCompare.put(item.getKey(), getHashMap(DEL, valueMap1));
            } else if (!map1.containsKey(item.getKey())) {
                resultCompare.put(item.getKey(), getHashMap(ADD, valueMap2));
            } else {
                if (Objects.equals(valueMap1, valueMap2)) {
                    resultCompare.put(item.getKey(), getHashMap(NOT_MOD, item.getValue()));
                } else {
                    resultCompare.put(item.getKey(), getHashMap(valueMap1, valueMap2));
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

    private static HashMap<String, Object> getHashMap(Object val1, Object val2) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(Comparator.MOD_OLD, val1);
        result.put(Comparator.MOD_NEW, val2);
        return result;
    }
}

