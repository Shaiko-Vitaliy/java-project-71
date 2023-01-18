package hexlet.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static String readingFileJson(String filePatch) {
        var result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePatch))) {
            String s;
            StringBuilder resBuilder = new StringBuilder(result);
            while ((s = br.readLine()) != null) {
                resBuilder.append(s);
                resBuilder.append("\n");
                result = resBuilder.toString();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public static String givesFormatInputFile(String filePatch) {
        if (!filePatch.contains(".")) {
            return "";
        }
        var indexOfSeparator = filePatch.lastIndexOf(".");
        return filePatch.substring(indexOfSeparator + 1);
    }
}
