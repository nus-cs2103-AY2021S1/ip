package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Personal chat bot to keep track of user's tasks.
 */
public class Duke {
    /**
     * The file path of where the data is stored.
     */
    private static final Path filePath = Paths.get(".", "data", "duke.txt");

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new Duke Object.
     * Tries to load existing tasks from an existing data file.
     * If that fails, a new task list instance is made.
     */
    public Duke() {
        this.storage = new Storage(Duke.filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.getTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets a response from the duke chat bot to reply the user input.
     *
     * @param input input by the user through the GUI.
     * @return the response given by Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}
