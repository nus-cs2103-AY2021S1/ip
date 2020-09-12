package duke;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import duke.core.DukeLogger;
import javafx.application.Application;

/**
 * Main entry point of application
 * Deals with args and decides whether to run the cmd or gui application
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * The main entry point of application
     * @param args Program arguments
     */
    public static void main(String[] args) {

        String usage = "Usage: java -jar duke.jar <cmd|gui>";

        // Default to GUI for jar file
        if (args.length == 0) {
            setupLogger(Level.FINE);
            runGui(args);
        }

        if (args.length != 1) {
            System.out.println(usage);
            return;
        }

        // Run either CMD or GUI depending on program argument
        switch (args[0]) {
        case "cmd":
            setupLogger(Level.OFF);
            runCmd(args);
            break;
        case "gui":
            setupLogger(Level.FINE);
            runGui(args);
            break;
        default:
            System.out.println(usage);
        }
    }

    private static void setupLogger(Level logLevel) {
        // Clear existing log handlers
        LogManager.getLogManager().reset();
        // Add console log
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(logLevel);
        Logger rootLogger = Logger.getLogger("");
        rootLogger.addHandler(consoleHandler);
        // Add duke file log
        DukeLogger.setLevel(Level.INFO);
        DukeLogger.addOutputFile("duke.log", false);
    }

    private static void runCmd(String[] args) {
        logger.info(Main.class.getSimpleName() + ": Starting DukeCmd");
        duke.cmd.Duke duke = new duke.cmd.Duke();
        duke.run();
    }

    private static void runGui(String[] args) {
        logger.info(Main.class.getSimpleName() + ": Starting DukeGui");
        // Use Application.launch to workaround classpath issues
        Application.launch(duke.gui.Duke.class, args);
    }

}
