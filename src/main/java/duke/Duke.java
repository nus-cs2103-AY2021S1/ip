package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;

/**
 * A class for duke.
 * Contains the main method to run duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Empty constructor for duke.
     *
     * Provides a path by default.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeStorageException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Public constructor of duke.
     *
     * Requires a file path of String type to
     * initiate storage for loading local saved tasks, if a
     * saved list is not found, it will automatically create a new
     * empty list.
     * @param filePath A file path of the task list file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeStorageException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parse and process the input command, then
     * retrieve the result from ui.
     */
    public ResultInfo getResponse(String input) {
        try {
            ui.setStringBuilder(new StringBuilder());
            Command command = Parser.parse(input);
            command.execute(tasks, storage, ui);
            return new ResultInfo(true, command.isExit(), ui.getStringBuilder().toString());
        } catch (DukeException e) {
            return new ResultInfo(false, false, e.getMessage());
        }
    }

    /**
     * Returns welcome message
     * @return Returns welcome message
     */
    public String getWelcome() {
        return ui.getWelcome();
    }
}
