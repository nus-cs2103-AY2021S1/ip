import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import nekochan.CliWrapper;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Entry point for the chat bot.
     * Execute the jar file with "-mode cli" as arguments to access command line interface.
     * Otherwise, defaults to graphical user interface.
     */
    public static void main(String[] args) {
        // Adapted from https://stackoverflow.com/a/1254338
        List<String> arguments = Arrays.asList(args);
        int modeIndex = arguments.indexOf("-mode");
        String mode = modeIndex == -1 ? "gui" : arguments.get(modeIndex + 1);
        if (mode.equals("gui")) {
            Application.launch(Main.class, args);
        } else if (mode.equals("cli")) {
            String[] defaultArgs = {};
            CliWrapper.main(defaultArgs);
        }
    }
}
