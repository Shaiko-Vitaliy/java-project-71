package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    public void generateInputJsonTest() {
        var actualResult = Differ.generate(FilePaths.FIRST_FILE_PATCH_JSON, FilePaths.SECOND_FILE_PATCH_JSON);
        assertEquals(FilePaths.EXPECTED_STYLISH, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");

    }

    @Test
    public void generateInputYamlTest() {
        String actualResult = Differ.generate(FilePaths.FIRST_FILE_PATCH_YAML,
                FilePaths.SECOND_FILE_PATCH_YAML, "stylish");
        assertEquals(FilePaths.EXPECTED_STYLISH, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatJsonTest() throws Exception {
        String actualResult = Differ.generate(FilePaths.FIRST_FILE_PATCH_YAML,
                FilePaths.SECOND_FILE_PATCH_YAML, "json");
        assertEquals(FilePaths.EXPECTED_JSON, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatPlainTest() throws Exception {
        String actualResult = Differ.generate(FilePaths.FIRST_FILE_PATCH_YAML,
                FilePaths.SECOND_FILE_PATCH_YAML, "plain");
        assertEquals(FilePaths.EXPECTED_PLAIN, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
