package alice;

import alice.ui.controller.AliceGui;
import javafx.application.Application;

/**
 * The main entry point to the Application
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            // Start CLI interface
            new Alice().initCli().run();
            return;
        }

        // By default start GUI interface
        Application.launch(AliceGui.class, args);
    }
}
