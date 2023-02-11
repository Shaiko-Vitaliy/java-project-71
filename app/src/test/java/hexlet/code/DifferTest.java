package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    public void generateInputJsonTest() {
        var actualResult = Differ.generate(Constants.FIRST_FILE_PATCH_JSON, Constants.SECOND_FILE_PATCH_JSON);
        assertEquals(Constants.EXPECTED_STYLISH, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");

    }

    @Test
    public void generateInputYamlTest() {
        String actualResult = Differ.generate(Constants.FIRST_FILE_PATCH_YAML,
                Constants.SECOND_FILE_PATCH_YAML, "stylish");
        assertEquals(Constants.EXPECTED_STYLISH, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла YAML и вывести результат в формате \"stylish\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatJsonTest() throws Exception {
        String actualResult = Differ.generate(Constants.FIRST_FILE_PATCH_YAML,
                Constants.SECOND_FILE_PATCH_YAML, "json");
        assertEquals(Constants.EXPECTED_JSON, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"json\" или результат не совпал с ожидаемым");
    }

    @Test
    public void outFormatPlainTest() throws Exception {
        String actualResult = Differ.generate(Constants.FIRST_FILE_PATCH_YAML,
                Constants.SECOND_FILE_PATCH_YAML, "plain");
        assertEquals(Constants.EXPECTED_PLAIN, actualResult, "Differ.generate() не получилось сравнить"
                + " два файла JSON и вывести результат в формате \"plain\" или результат не совпал с ожидаемым");
    }
}
