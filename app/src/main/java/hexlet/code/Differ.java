package hexlet.code;

import hexlet.code.formatters.Format;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        String result = "{}";
        var absolutPatchFirst = getFullPath(firstFilePath);
        var absolutPatchSecond = getFullPath(secondFilePath);
        Map<String, Object> mapFromFirstFile = new HashMap<>();
        Map<String, Object> mapFromSecondFile = new HashMap<>();
        try {
            mapFromFirstFile = getData(absolutPatchFirst);
            mapFromSecondFile = getData(absolutPatchSecond);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
            var resultCompare = Comparator.compare(mapFromFirstFile, mapFromSecondFile);
        try {
            result = Format.format(resultCompare, format);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static String generate(String firstFilePath, String secondFilePath) {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static String givesFormatInputFile(String filePath) {
        if (!filePath.contains(".")) {
            return "";
        }
        var indexOfSeparator = filePath.lastIndexOf(".");
        return filePath.substring(indexOfSeparator + 1);
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Map<String, Object> getData(Path path) throws Exception {
        String lineFromFile = "";
        lineFromFile = Files.readString(path);
        var formatInputFile = givesFormatInputFile(path.toString());
        return Parser.parse(lineFromFile, formatInputFile);
    }
}