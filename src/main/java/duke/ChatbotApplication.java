package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIoException;
import duke.exceptions.DukeUnknownException;
import duke.gui.MainLauncher;
import duke.ui.CommandLineInterface;
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

    public static void main(String[] args) {
        UserInterface ui = new CommandLineInterface();
        Duke duke = new Duke(ui);
        while (ui.isRunning()) {
            duke.nextIteration();
        }
//        if (args.length > 0 && args[0].equals("--gui")) {
//            //Application.launch(MainLauncher.class, args);
//        } else {
//            
//        }
    }
}
