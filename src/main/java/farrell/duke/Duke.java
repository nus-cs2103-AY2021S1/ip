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
    private DataManager dataManager;

    /**
     * Creates a new instance of the program in the default configuration.
     * Data is saved/loaded form data/data.txt.
     * The task list is initially empty.
     */
    public Duke() {
        dataManager = new DataManager();
        taskList = new TaskList();
    }

    /**
     * Creates a new instance of the program that saves and loads data from a specified file path.
     * @param dataPath The file to save/load from.
     */
    public Duke(String dataPath) {
        try {
            dataManager = new DataManager(dataPath);
            taskList = dataManager.load();
        } catch (DukeException exception) {
            taskList = new TaskList();
        }
    }

    /**
     * Starts a new instance of the program.
     * Existing data is loaded (if available).
     *
     * @param uiManager The UIManager to handle the startup error messages.
     */
    public Duke(UiManager uiManager) {
        try {
            dataManager = new DataManager();
            taskList = dataManager.load();
        } catch (DukeException exception) {
            uiManager.sendDukeError(exception.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs an appropriate command corresponding to the input string.
     *
     * @param input The input to process.
     * @return The output message of the command that was executed.
     * @throws DukeException If there were problems encounted with parsing the input or executing the command.
     */
    public String run(String input) throws DukeException {
        Command command = CommandParser.parse(input);
        String output = command.execute(taskList);

        dataManager.save(taskList);

        if (command.shouldExit()) {
            System.exit(0);
        }

        return output;
    }
}
