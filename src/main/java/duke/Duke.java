package duke;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * <h1>Duke Task Tracker</h1>
 * The Duke program implements a bot that will help
 * users keep track of the different tasks they input.
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        storage = new Storage();
        try {
            // storage.clear();
            taskList = new TaskList(storage.readData());
            ui = new Ui(taskList, storage);
        } catch (IOException e) {
            taskList = new TaskList();
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the user input is "bye" so that the program can close
     *
     * @return True if user input is "bye"
     */
    public boolean isBye() {
        return ui.isBye();
    }

    /**
     * Gets the correct response that should be said by the bot following the user's input.
     *
     * @param input User's input.
     * @return String containing the response by the bot according to the user's input.
     */
    public String getResponse(String input) {
        try {
            return ui.runProgram(input);
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * Retrieves the designated greeting whenever the program starts.
     *
     * @return String containing the greeting to be said by the bot when the program starts.
     */
    public String getGreeting() {
        return ui.greet();
    }

}
