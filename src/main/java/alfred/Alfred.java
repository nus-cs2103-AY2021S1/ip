package alfred;

import java.nio.file.Path;
import java.nio.file.Paths;

import alfred.command.Command;
import alfred.task.TaskList;

/**
 * A personal assistant chat bot to help users keep track of tasks.
 */
public class Alfred {

    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Path filePath;

    /**
     * Creates a new instance of the Alfred chat bot with the default filepath.
     */
    public Alfred() {
        this(Paths.get("data", "alfred.txt"));
    }

    /**
     * Creates a new instance of the Alfred chat bot with a custom filepath.
     *
     * @param filePath File path where Alfred should read from and save to.
     */
    public Alfred(Path filePath) {
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
        } catch (AlfredException e) {
            return false;
        }
    }

    /**
     * Returns the response given by Alfred to a command.
     *
     * @param input Argument supplied for command.
     * @return Response by Alfred to command.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        Command command = Parser.parse(input);
        try {
            command.execute(storage, taskList, ui);
            return ui.getNextResponse();
        } catch (AlfredException e) {
            return e.getMessage();
        }
    }
}
