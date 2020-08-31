package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the driver class to run the Duke programme.
 */
public class Duke {

    /**
     * A storage instance to load and save tasks.
     */
    private final Storage storage;

    /**
     * A task manager to handle task operations.
     */
    private TaskManager manager;

    /**
     * A ui instance to handle user interactions.
     */
    private final Ui ui;

    /**
     * Initializes all class variables.
     *
     * @param filePath The file path for the storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            manager = new TaskManager(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager();
        }
    }

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Returns string representation of the response by Duke.
     *
     * @param input The input message by the user.
     * @return The response by Duke after processing the input message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(manager, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
