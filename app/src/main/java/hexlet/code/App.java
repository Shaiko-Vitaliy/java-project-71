package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App {
    // @Option(names = {"-f", "--format"}, paramLabel = "format",description = "output format [default: stylish]")
    // int format;
    // @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    //boolean usageHelpRequested;
    //@Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    // boolean versionInfoRequested;
    @Parameters(index = "0", description = "path to first file")
    private static File file1 ;
    //@Parameters(index = "1", description = "path to second file")
    // private File filepath2;

    private static Map<String, Object> filepatch1Map = new HashMap<>();
    private static Map<String, Object> filepatch2Map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        filepatch1Map = Differ.makesFromJsonToMap(new File("/home/vitaliy/javaProject/project71/java-project-71/app/src/main/resources/filepath1.json"));
        filepatch2Map = Differ.makesFromJsonToMap(new File("/home/vitaliy/javaProject/project71/java-project-71/app/src/main/resources/filepath2.json"));
        System.out.println(Differ.generate(filepatch1Map, filepatch2Map));
    }
}