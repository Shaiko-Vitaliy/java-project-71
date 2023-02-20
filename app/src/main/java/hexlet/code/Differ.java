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
        var absolutPathFirst = getFullPath(firstFilePath);
        var absolutPathSecond = getFullPath(secondFilePath);
        Map<String, Object> dataFromFirstFile = new HashMap<>(); //инициализируем из за try catch
        Map<String, Object> dataFromSecondFile = new HashMap<>(); //инициализируем из за try catch
        try {
            dataFromFirstFile = getData(absolutPathFirst);
            dataFromSecondFile = getData(absolutPathSecond);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        var resultCompare = Comparator.findDiff(dataFromFirstFile, dataFromSecondFile);
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

    private static String giveFormatInputFile(String filePath) {
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
        var formatInputFile = giveFormatInputFile(path.toString());
        return Parser.parse(lineFromFile, formatInputFile);
    }
}
