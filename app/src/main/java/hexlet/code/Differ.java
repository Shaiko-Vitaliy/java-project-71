package hexlet.code;

import hexlet.code.formatters.Format;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) {
        String result = "{}";
        try {
            var absolutPatchFirst = getFullPath(firstFilePath);
            var absolutPatchSecond = getFullPath(secondFilePath);
            var mapFromFirstFile = getData(absolutPatchFirst);
            var mapFromSecondFile = getData(absolutPatchSecond);
            var resultCompareMaps = Comparator.comparesTwoMaps(mapFromFirstFile, mapFromSecondFile);
            result = Format.getResultInOutFormat(resultCompareMaps, format);
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

    private static Path getFullPath(String filePath) throws InvalidPathException {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Map<String, Object> getData(Path path) throws Exception {
        String lineFromFile = "";
        lineFromFile = Files.readString(path);
        var formatInputFile = givesFormatInputFile(path.toString());
        return Parser.parsingFile(lineFromFile, formatInputFile);
    }
}
