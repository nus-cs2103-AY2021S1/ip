package main.java.farrell.duke;

import main.java.farrell.duke.command.Command;
import main.java.farrell.duke.command.CommandParser;
import main.java.farrell.duke.gui.UiManager;
import main.java.farrell.duke.task.TaskList;

/**
 * A personal assistant chatbot that helps to keep track of tasks.
 */
public class Duke {
    /** The list of tasks being tracked */
    private TaskList taskList;

    /** An object that handles saving and loading data */
    private DataManager dataManager = new DataManager();

    /**
     * Starts a new instance of the program.
     * Existing data is loaded (if available).
     * Display a startup message.
     * @param uiManager The UIManager to handle the startup error messages.
     * @throws DukeException If unable to load data
     */
    public Duke(UiManager uiManager) {
        try {
            taskList = dataManager.load();
        } catch (DukeException exception) {
            uiManager.sendDukeMessage(exception.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Displays a welcome message, then runs the main program loop.
     * The main program loop consists of the following:
     * 1. Obtaining user input.
     * 2. Parsing user input to the corresponding command.
     * 3. Execute the command.
     * 4. Display the program output to the user.
     */
    public String run(String input) {
        try {
            Command command = CommandParser.parse(input);
            String output = command.execute(taskList);

            dataManager.save(taskList);

            if (command.shouldExit()) {
                System.exit(0);
            }

            return output;
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }
}
