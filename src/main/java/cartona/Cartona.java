package cartona;

import cartona.command.Command;
import cartona.command.Parser;
import cartona.exception.CartonaException;
import cartona.task.TaskList;
import cartona.ui.Ui;

import java.io.IOException;

/**
 * Cartona is a simple CLI to-do list application. When run from the console, it reads input from the user and
 * adds and deletes items (called Tasks). It also saves the running list tasks to a file that it loads when run.
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

    public String load() {
        String lineMessage = "";

        try {
            if (!storage.checkAndCreateFile()) {
                lineMessage = "     Existing list not found, creating new list\n";
            }
        } catch (IOException e) {
            return ui.getErrorMessageFormatted(e.getMessage());
        }

        this.taskList = storage.getListFromStorage();

        if (taskList.getSize() == 0) {
            lineMessage = "     Found an existing list, but it was empty!\n";
        } else {
            lineMessage = String.format("     Found an existing list at %s%n", storage.getPath());
        }

        return String.format("%s%s", lineMessage, ui.getWelcomeMessageFormatted());
    }

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
