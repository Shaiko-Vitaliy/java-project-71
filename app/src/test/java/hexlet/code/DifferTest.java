package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String resourcesPatch;
    private static String firstFilePatch;
    private static String secondFilePatch;
    private static String expectedFileComprasion;
    @BeforeAll
    public static void beforeAll() {
        resourcesPatch = new File("src/test/resources").getAbsolutePath();
        firstFilePatch = resourcesPatch + "/json/firstFile.json";
        secondFilePatch = resourcesPatch + "/json/secondFile.json";
        expectedFileComprasion = """
                {
                    date: 2023
                  - follow: false
                  - host: shaiko.com
                  + host: hexlet.io
                  + id: 456
                  - name: null
                  + name: Stenli
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        System.out.println(expectedFileComprasion);

    }
    @Test
    public void generateTest() throws IOException {
        String actualResult = Differ.generate(firstFilePatch, secondFilePatch, "format");
        assertEquals(expectedFileComprasion, actualResult);
    }
}
