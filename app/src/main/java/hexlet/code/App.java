package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    private static final int SUCCESSFUL_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;
@Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
String format;
@Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
private boolean usageHelpRequested;
@Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
private boolean versionInfoRequested;
@Parameters(index = "0", description = "path to first file")
private String firstFilePatch;
@Parameters(index = "1", description = "path to second file")
private String secondFilePatch;

    @Override
    public final Integer call() {
        try {
            var diff = Differ.generate(firstFilePatch, secondFilePatch, format);
            System.out.println(diff);
            return SUCCESSFUL_EXIT_CODE;
        } catch (Exception e) {
            return ERROR_EXIT_CODE;
        }
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}