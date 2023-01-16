package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String firstFilePatch, String secondFilePatch, String format) throws IOException {
        Map<String, Object> mapFromFirstFile = makesFromJsonToMap(firstFilePatch);
        Map<String, Object> mapFromSecondFile = makesFromJsonToMap(secondFilePatch);
        var resultCompareMaps = comparesTwoMaps(mapFromFirstFile, mapFromSecondFile);
        var arrayFromLine = resultCompareMaps.split("\n");
        var arraySort = sort(arrayFromLine);
        return makeFromArraystoString(arraySort);
    }

    private static Map<String, Object> makesFromJsonToMap(String filepatch) throws IOException {
        Map<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        var result = "";
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
        map = objectMapper.readValue(result, new TypeReference<>() {
        });
        return map;
    }
    private static String comparesTwoMaps(Map<String, Object> map1, Map<String, Object> map2) {
        var result = "";
        StringBuilder builder = new StringBuilder(result);
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
        return builder.toString();
    }

    private static String[] sort(String[] arr) {
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

    private static String makeFromArraystoString(String[] array) {
        StringBuilder builder = new StringBuilder("");
        builder.append("{\n")
                .append("  ")
                .append(array[0])
                .append("\n");
        for (var i = 1; i < array.length - 1; i++) {
            if (i < array.length - 1 && i > 0) {
                builder.append("  ")
                        .append(array[i])
                        .append("\n");
            }
        }
        builder.append("  ")
                .append(array[array.length - 1]).append("\n")
                .append("}");
        return builder.toString();
    }
}
