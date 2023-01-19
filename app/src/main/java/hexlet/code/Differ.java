package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String firstFilePatch, String secondFilePatch, String format) throws Exception {
        Map<String, Object> mapFromFirstFile = Parser.parsingFile(firstFilePatch);
        if (mapFromFirstFile.isEmpty()) {
            System.out.println("Первый файл не верного формата или пустой!");
        }
        Map<String, Object> mapFromSecondFile = Parser.parsingFile(secondFilePatch);
        if (mapFromSecondFile.isEmpty()) {
            System.out.println("Второй файл не верного формата или пустой!");
        }
        if (mapFromSecondFile.isEmpty() && mapFromFirstFile.isEmpty()) {
            return "Не удалось сравнить файлы";
        }
        var resultCompareMaps = comparesTwoMaps(mapFromFirstFile, mapFromSecondFile);
        //System.out.println(resultCompareMaps);
        var arrayFromLine = resultCompareMaps.split("\n");
        var arraySort = sort(arrayFromLine);
        return makeFromArraystoString(arraySort);
    }

    private static String comparesTwoMaps(Map<String, Object> map1, Map<String, Object> map2) {
        var result = "";
        StringBuilder builder = new StringBuilder(result);
        //System.out.println(map1.toString());
        //System.out.println(map2.toString());
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
        return arr;
    }

    private static String makeFromArraystoString(String[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n")
                .append("  ")
                .append(array[0])
                .append("\n");
        for (var i = 1; i < array.length - 1; i++) {
            if (i < array.length - 1) {
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
