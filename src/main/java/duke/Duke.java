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
            return c.execute(tm, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
