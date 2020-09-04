package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.task.TaskList;

/**
 * A personal assistant chat bot to help users keep track of tasks.
 */
public class Duke {

    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Path filePath;

    /**
     * Creates a new instance of the Duke chat bot with the default filepath.
     */
    public Duke() {
        this(Paths.get("data", "duke.txt"));
    }

    /**
     * Creates a new instance of the Duke chat bot with a custom filepath.
     *
     * @param filePath File path where Duke should read from and save to.
     */
    public Duke(Path filePath) {
        this.filePath = filePath;
        this.ui = new UI();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
    }

    /**
     * Populates the task list with the data stored in local storage if the
     * data is accessible.
     *
     * @return True if data successfully populated. False otherwise.
     */
    public boolean loadedFromStorage() {
        try {
            storage.loadFromStorage();
            taskList.loadDataFromStorage(filePath);
            return true;
        } catch (DukeException e) {
            return false;
        }
    }

    /**
     * Returns the response given by Duke to a command.
     *
     * @param input Argument supplied for command.
     * @return Response by Duke to command.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        Command command = Parser.parse(input);
        try {
            command.execute(storage, taskList, ui);
            return ui.getNextResponse();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error: Task could not be saved.";
        }
    }
}
