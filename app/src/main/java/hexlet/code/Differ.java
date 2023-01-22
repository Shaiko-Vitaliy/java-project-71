package hexlet.code;

import hexlet.code.formatters.Format;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String firstFilePatch, String secondFilePatch, String format) throws Exception {
        var lineFromFirstFile = Files.readString(Paths.get(firstFilePatch));
        var formatInputFirstFile = givesFormatInputFile(firstFilePatch);
        var lineFromSecondFile = Files.readString(Paths.get(secondFilePatch));
        var formatInputSecondFile = givesFormatInputFile(secondFilePatch);

        var mapFromFirstFile = Parser.parsingFile(lineFromFirstFile, formatInputFirstFile);
        var mapFromSecondFile = Parser.parsingFile(lineFromSecondFile, formatInputSecondFile);

        displaysMessage(mapFromFirstFile, mapFromSecondFile);

        TreeMap<String, Object> sortedMap = makeTreeMap(mapFromFirstFile, mapFromSecondFile);
        var resultCompareMaps = Comparator.comparesTwoMaps(mapFromFirstFile, mapFromSecondFile, sortedMap);
        return Format.getResultInOutFormat(resultCompareMaps, format);
    }

    private static String givesFormatInputFile(String filePatch) {
        if (!filePatch.contains(".")) {
            return "";
        }
        var indexOfSeparator = filePatch.lastIndexOf(".");
        return filePatch.substring(indexOfSeparator + 1);
    }

    private static TreeMap<String, Object> makeTreeMap(Map<String, Object> map1, Map<String, Object> map2) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(map1);
        treeMap.putAll(map2);
        return treeMap;
    }

    private static void displaysMessage(Map<String, Object> map1, Map<String, Object> map2) {
        if (map1.isEmpty()) {
            System.out.println("Первый файл не верного формата или пустой!");
        } else if (map2.isEmpty()) {
            System.out.println("Второй файл не верного формата или пустой!");
        }
    }
}
