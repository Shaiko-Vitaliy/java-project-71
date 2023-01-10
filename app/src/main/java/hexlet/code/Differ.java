package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Differ {
    public static Map<String, Object> map1 = new HashMap<>();
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static String result = "";
    public static Map<String, Object> makesFromJsonToMap(File filepatch) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepatch))) {
            String s;
            StringBuilder resBuilder = new StringBuilder(result);
            while ((s = br.readLine()) != null) {
                resBuilder.append(s);
                result = resBuilder.toString();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        map1 = objectMapper.readValue(result, new TypeReference<Map<String,Object>>(){});
        result = "";
        return map1;
    }
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        var result ="";
        StringBuilder builder = new StringBuilder(result);
        StringBuilder builderTemp = new StringBuilder(result);
        for (Map.Entry<String, Object> item : map1.entrySet()) {
            if (!map2.containsKey(item.getKey())) {
                builder.append("- " + item.getKey() + ":" + " " + item.getValue() + "\n");
            }
            if (map2.containsKey(item.getKey())) {
                if (!map2.containsValue(item.getValue())) {
                    builder.append("- " + item.getKey() + ":" + " " + item.getValue() + "\n");
                    builder.append("+ " + item.getKey() + ":" + " " + map2.get(item.getKey()) + "\n");
                }
                if (map2.containsValue(item.getValue())) {
                    builder.append("  " + item.getKey() + ":" + " " + item.getValue() + "\n");
                }
            }
        }
        for (Map.Entry<String, Object> item2 : map2.entrySet()) {
            if (!map1.containsKey(item2.getKey())) {
                builder.append("+ " + item2.getKey() + ":" + " " + item2.getValue() + "\n");
            }
        }
        String temp = builder.toString();
        String[] tempArray = sort(creatingAnArray(temp));
        for (var i = 0; i < tempArray.length; i++) {
            builderTemp.append(tempArray[i] + "\n");
        }
        return builderTemp.toString();
    }

    public static String[] creatingAnArray (String str) {
        String[] arr = str.split("\n");
        return arr;
    }
    public static String[] sort(String[] arr) {
        String temp = "";
        boolean neddIteration = true;
        while (neddIteration) {
            neddIteration = false;
            for (var i = 1; i < arr.length; i++) {
                if (arr[i].charAt(2) < arr[i - 1].charAt(2)) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    neddIteration = true;
                }
            }
        }
        temp = "";
        return arr;
    }
}