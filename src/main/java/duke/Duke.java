package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandParser;
import duke.exception.DateParseException;
import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.TaskList;

/**
 * Duke is the class encapsulating all application processes.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises Duke class.
     * @throws DateParseException if Task created from file information cannot be stored in local storage.
     * @throws StorageException if Task date (if any) cannot be parsed into LocalDate object.
     */
    public Duke() throws DateParseException, StorageException {
        this.storage = new Storage();
        this.taskList = TaskList.initialiseTaskList(this.storage);
    }

    /**
     * Launches and runs the application.
     * @param args Standard arguments
     */
    public static void main(String[] args) {
        try {
            Storage storage = new Storage();
            TaskList taskList = TaskList.initialiseTaskList(storage);

            Scanner scanner = new Scanner(System.in);
            Ui.printWelcomeMessage();
            boolean isExit = false;

            while (!isExit) {
                try {
                    String userCommand = scanner.nextLine();
                    Command parsedCommand = CommandParser.parseCommand(userCommand);
                    parsedCommand.execute(taskList, storage);
                    isExit = parsedCommand.shouldExit();
                } catch (DukeException e) {
                    Ui.printErrorMessage(e.getUiMessage());
                }
            }
            scanner.close();
        } catch (DukeException e) {
            Ui.printErrorMessage(e.getUiMessage());
        }
    }

    public static String sendWelcomeMessage() {
        return Ui.printWelcomeMessage();
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
            return Ui.printErrorMessage(e.getUiMessage());
        }
    }
}
