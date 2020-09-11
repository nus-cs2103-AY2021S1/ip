package duke;

import duke.ui.CommandLineInterface;
import duke.ui.MainLauncher;
import duke.ui.UserInterface;
import javafx.application.Application;


/**
 * Front End Facing Script for the UI of duke.ChatbotApplication
 */
public class ChatbotApplication {
    private final Duke dukeProgram;
    /**
     * Constructor class of the duke.ChatbotApplication
     * @param ui the path to read a file from.
     */
    public ChatbotApplication(UserInterface ui) {
        dukeProgram = new Duke(ui);
    }

    /**
     * Entry point for application
     * @param args args
     */
    public static void main(String[] args) {
        //args[0] = "cli";
        if (args.length > 0 && args[0].equals("-cli")) {
            UserInterface ui = new CommandLineInterface();
            Duke duke = new Duke(ui);
            while (ui.isRunning()) {
                duke.nextIteration();
            }
        } else {
            Application.launch(MainLauncher.class, args);
        }
    }
}
