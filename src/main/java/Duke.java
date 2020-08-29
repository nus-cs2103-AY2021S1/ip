import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Main class to run Focus.
 */
public class Duke {
    /**
     * Storage created for user.
     */
    private final Storage storage;
    /**
     * Task list created for user.
     */
    private final TaskList taskList;
    /**
     * UI created to interact with user.
     */
    private final UI ui;

    /**
     * Creates Focus to set up the things needed.
     */
    public Duke() {
        ui = new UI();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            taskList = new TaskList(storage.loadData());
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Gets response from Focus based on user's input.
     * @param input User's input.
     * @return Focus' response.
     */
    public String getResponse(String input) {
        boolean exit;
        Command command = Parser.parse(input);
        exit = command.isExit();
        if (exit) {
            return ui.exitFocus();
        }
        try {
            return command.execute(input, taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
