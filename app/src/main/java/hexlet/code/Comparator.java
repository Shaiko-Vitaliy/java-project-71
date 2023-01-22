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
        var del = "delet";
        var modOld = "Old modified";
        var modNew = "New modified";
        var notMod = "not modified";
        Map<String, HashMap<String, Object>> resultCompare = new TreeMap<>();
        for (Map.Entry<String, Object> item : sortedMap.entrySet()) {
            var valueMap1 = map1.get(item.getKey());
            var valueMap2 = map2.get(item.getKey());
            if (!map2.containsKey(item.getKey())) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put(del, valueMap1);
                resultCompare.put(item.getKey(), temp);
            } else if (!map1.containsKey(item.getKey())) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put(add, valueMap2);
                resultCompare.put(item.getKey(), temp);
            } else {
                if (Objects.equals(valueMap1, valueMap2)) {
                    HashMap<String, Object> temp = new HashMap<>();
                    temp.put(notMod, item.getValue());
                    resultCompare.put(item.getKey(), temp);
                } else {
                    HashMap<String, Object> temp = new HashMap<>();
                    temp.put(modOld, valueMap1);
                    temp.put(modNew, valueMap2);
                    resultCompare.put(item.getKey(), temp);
                }

            }
        }
        return resultCompare;
    }
}

