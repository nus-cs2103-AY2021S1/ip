package alice;

import alice.ui.AliceGui;
import javafx.application.Application;

/**
 * The main entry point to the Alice
 */
public class Launcher {
    /**
     * Starts the CLI or GUI depending on command line arguments.
     * Start up Alice GUI by default. Start the CLI by using "-c"
     * in the argument.
     *
     * @param args a string array containing the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            // Start CLI interface
            new Alice().initialiseCli().run();
            return;
        }

        // By default start GUI interface
        Application.launch(AliceGui.class, args);
    }
}
