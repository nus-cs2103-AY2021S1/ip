package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.utils.DukeState;

/**
 * The Duke object initializes the core classes Storage and TaskList, and contains the main logic
 * to respond correctly to user input.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private DukeState dukeState;
    private final boolean isLoadedFromDisk;

    /**
     * Initializes a Duke object. The taskList will be loaded from disk if there exists
     * an existing data file. Else, Duke will start with an empty taskList.
     *
     * @param filePath the filePath where the storage will load from and save data to.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);

        TaskList tmpTaskList;
        boolean tmpIsLoadedFromDisk;
        try {
            tmpTaskList = new TaskList(storage.load());
            tmpIsLoadedFromDisk = true;
        } catch (DukeException e) {
            tmpTaskList = new TaskList();
            tmpIsLoadedFromDisk = false;
        }

        this.taskList = tmpTaskList;
        this.dukeState = DukeState.RUNNING;
        this.isLoadedFromDisk = tmpIsLoadedFromDisk;
    }

    /**
     * Gets the appropriate response for a given user input.
     * @param input The user input.
     * @return The appropriate response.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parseInput(input);
            if (cmd.isExit()) {
                dukeState = DukeState.EXITED;
            }
            return cmd.execute(storage, taskList);
        } catch (DukeException e) {
            return e.getPrettyErrorMsg();
        }
    }

    /**
     * Gets the current state of the Duke application.
     * @return The current state of the Duke application.
     */
    public DukeState getDukeState() {
        return dukeState;
    }

    /**
     * Checks whether the taskList was loaded from a data file on app-start.
     * @return A boolean value indicating whether the taskList was loaded from a data file.
     */
    public boolean isLoadedFromDisk() {
        return isLoadedFromDisk;
    }
}
