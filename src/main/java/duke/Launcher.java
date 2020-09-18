package duke;

import duke.ui.Cli;
import duke.ui.GuiLauncher;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Starts Duke either as a GUI or CLI program. To start Duke as a CLI program, the first value
     * in args must be the String "cli". This can be done by running "java -jar duke.jar cli" in the
     * terminal. Otherwise, Duke is launched as a GUI.
     *
     * @param args Array of command line arguments.
     */
    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("cli")) {
            new Cli();
        } else {
            new GuiLauncher();
        }
    }
}
