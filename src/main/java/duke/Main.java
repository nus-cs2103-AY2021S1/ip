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
        if (args.length > 0) {
            if (args[0].equals("cmd")) {
                runCmd(args);
            } else if (args[0].equals("gui")) {
                runGui(args);
            } else {
                System.out.println(args[0]);
                System.out.println("Usage: java main <cmd|gui>");
            }
        }
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
