package cartona;

import java.io.IOException;

import cartona.command.Command;
import cartona.command.Parser;
import cartona.exception.CartonaException;
import cartona.task.TaskList;
import cartona.ui.Ui;


/**
 * Cartona is a simple to-do list application with JavaFX GUI. When run from the console, it reads input from the user
 * and adds and deletes items (called Tasks). It also saves the running list tasks to a file that it loads when
 * re-opened.
 *
 * @author Jaya Rengam
 */
public class Cartona {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    Cartona() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage("./tasklist.txt");
    }

    /**
     * Loads the saved list of tasks from the text file into the taskList.
     * Used when the Cartona instance is first created.
     *
     * @return a String describing the result of attempting to load a list from the text file, along with
     *         the welcome message
     */
    public String load() {
        String lineMessage = "";

        try {
            if (!storage.checkAndCreateFile()) {
                lineMessage = " Existing list not found, creating new list\n";
            } else {
                this.taskList = storage.getListFromStorage();

                if (taskList.getSize() == 0) {
                    lineMessage = " Found an existing list, but it was empty!\n";
                } else {
                    lineMessage = String.format(" Found an existing list at %s%n", storage.getPath());
                }
            }

            return String.format("%s%s", lineMessage, ui.getWelcomeMessageFormatted());

        } catch (IOException e) {
            return ui.getErrorMessageFormatted(e.getMessage());
        }
    }

    /**
     * Processes the input string from the user (via the GUI) and returns a message to be printed to the GUI.
     *
     * @param inputString the input entered by the user into the GUI
     * @return the message returned when a relevant Command is successfully executed, or an error message.
     */
    public String getResponse(String inputString) {
        try {
            // Parse and execute command
            Command nextCommand = parser.parseCommand(inputString);

            String response = nextCommand.execute(taskList, ui, storage);
            return response;

        } catch (CartonaException e) {
            // If errors are encountered, print the error message to the console.
            return ui.getErrorMessageFormatted(e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
