package duke;

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

        String usage = "Usage: java -jar duke.jar <cmd|gui>";

        if (args.length != 1) {
            System.out.println(usage);
            return;
        }

        // Run either CMD or GUI depending on program argument
        switch (args[0]) {
        case "cmd":
            runCmd(args);
            break;
        case "gui":
            runGui(args);
            break;
        default:
            System.out.println(usage);
        }

    }

    private static void runCmd(String[] args) {
        duke.cmd.Duke duke = new duke.cmd.Duke();
        duke.run();
    }

    private static void runGui(String[] args) {
        // Use Application.launch to workaround classpath issues
        Application.launch(duke.gui.Duke.class, args);
    }

}
