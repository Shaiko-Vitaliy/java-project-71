package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        var pathFirst = getFullPath(firstFilePath);
        var pathSecond = getFullPath(secondFilePath);
        Map<String, Object> dataFromFirstFile = getData(pathFirst);
        Map<String, Object> dataFromSecondFile = getData(pathSecond);
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

    private static Map<String, Object> getData(Path path) {
        String fileContent;
        File file = new File(path.toString());
        if (!file.isFile()) {
            System.err.println("The file does not exist by this path");
            throw new RuntimeException("The file does not exist by this path", new SecurityException());
        }
        try {
            fileContent = Files.readString(path);
        } catch (IOException e) {
            System.err.println(e.toString());
            throw new RuntimeException(e.toString());
        }
        var format = getFormat(path.toString());
        return Parser.parse(fileContent, format);
    }
}
