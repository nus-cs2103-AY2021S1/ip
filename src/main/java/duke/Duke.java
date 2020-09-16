package duke;

import duke.exceptions.DukeException;
import duke.logic.CommandParser;
import duke.logic.commands.Command;
import duke.model.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The Duke program is a chatbot for managing tasks and deadlines.
 *
 * @author Daniel Adipranoto
 * @version 0.1
 */
public class Duke {
    private static final String FILE_PATH = "data/DukeDB.txt";
    private Storage storage;
    private TaskManager tm;
    private Ui ui;

    // Current exit status. Acts as exit signal for MainWindow.
    private boolean isExit;

    /**
     * Constructor for Duke class.
     * Initiates the User Interface and tries to load save file from disk.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tm = new TaskManager((storage.load()));
        } catch (Exception e) {
            ui.showLoadingError();
            tm = new TaskManager();
        }
        isExit = false;
    }

    /**
     * Generates response to command input.
     *
     * @param input String input by user.
     * @return Response after command is executed.
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            isExit = c.isExit();
            return c.execute(tm, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the current exit status.
     * Acts as a exit signal for MainWindow.
     *
     * @return Current isExit value.
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Passes Welcome message from Ui class to MainWindow.
     *
     * @return Welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}
