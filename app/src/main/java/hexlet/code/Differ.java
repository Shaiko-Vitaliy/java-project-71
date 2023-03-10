package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        var pathFirst = getFullPath(firstFilePath);
        var pathSecond = getFullPath(secondFilePath);
        var dataFromFirstFile = getData(pathFirst);
        var dataFromSecondFile = getData(pathSecond);
        var resultCompare = Comparator.findDiff(dataFromFirstFile, dataFromSecondFile);
        return Formatter.format(resultCompare, format);
    }

    public static String generate(String firstPath, String secondPath) {
        return generate(firstPath, secondPath, "stylish");
    }

    private static String getFormat(String path) {
        if (!path.contains(".")) {
            return "";
        }
        var indexOfSeparator = path.lastIndexOf(".");
        return path.substring(indexOfSeparator + 1);
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Map<String, Object> getData(Path path) {
        var fileContent = "";
        try {
            fileContent = Files.readString(path);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        var format = getFormat(path.toString());
        return Parser.parse(fileContent, format);
    }
}
