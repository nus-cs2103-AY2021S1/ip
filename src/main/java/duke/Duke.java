package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Duke is the class encapsulating all application processes.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises Duke class.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = TaskList.initialiseTaskList(this.storage);
    }

    /**
     * Launches and runs the application.
     * @param args Standard arguments
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = TaskList.initialiseTaskList(storage);

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = scanner.nextLine();
                Command parsedCommand = CommandParser.parseCommand(userCommand);
                parsedCommand.execute(taskList, storage);
                isExit = parsedCommand.shouldExit();
            } catch (DukeException e) {
                DukeMessages.printErrorMessage(e.getUiMessage());
            }
        }
        scanner.close();
    }

    public static String sendWelcomeMessage() {
        return DukeMessages.printWelcomeMessage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String userCommand = input;
            Command parsedCommand = CommandParser.parseCommand(userCommand);
            return parsedCommand.execute(taskList, storage);
        } catch (DukeException e) {
            return DukeMessages.printErrorMessage(e.getUiMessage());
        }
    }
}
