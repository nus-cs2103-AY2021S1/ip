package duke;

import duke.cmd.Duke;
import javafx.application.Application;

/**
 * Main entry point of application
 * Deals with args and decides whether to run the cmd or gui application
 */
public class Main {

    /**
     * The main entry point of application
     * @param args Program arguments
     */
    public static void main(String[] args) {
        runGui(args);
    }

    private static void runCmd(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private static void runGui(String[] args) {
        // Use Application.launch to workaround classpath issues
        Application.launch(duke.gui.Duke.class, args);
    }

}
