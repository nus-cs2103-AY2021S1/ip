import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import nekochan.CliWrapper;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    private static final String FLAG_MODE = "-mode";
    private static final String FLAG_GUI = "gui";
    private static final String FLAG_CLI = "cli";

    /**
     * Entry point for the chat bot.
     * Execute the jar file with "-mode cli" as arguments to access command line interface.
     * Otherwise, defaults to graphical user interface.
     */
    public static void main(String[] args) {
        // Adapted from https://stackoverflow.com/a/1254338
        List<String> arguments = Arrays.asList(args);
        int modeIndex = arguments.indexOf(FLAG_MODE);
        String mode = modeIndex == -1 ? FLAG_GUI : arguments.get(modeIndex + 1);
        if (mode.equals(FLAG_GUI)) {
            Application.launch(Main.class, args);
        } else if (mode.equals(FLAG_CLI)) {
            String[] defaultArgs = {};
            CliWrapper.main(defaultArgs);
        }
    }
}
