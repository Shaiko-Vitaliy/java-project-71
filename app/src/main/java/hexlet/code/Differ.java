package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        var pathFirst = getFullPath(firstFilePath);
        var pathSecond = getFullPath(secondFilePath);
        Map<String, Object> dataFromFirstFile = new HashMap<>();
        Map<String, Object> dataFromSecondFile = new HashMap<>();
        try {
            dataFromFirstFile = getData(pathFirst);
            dataFromSecondFile = getData(pathSecond);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        var resultCompare = TreeComparator.findDiff(dataFromFirstFile, dataFromSecondFile);
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

    private static Map<String, Object> getData(Path path) throws IOException {
        var fileContent = Files.readString(path);
        var format = getFormat(path.toString());
        return Parser.parse(fileContent, format);
    }
}
