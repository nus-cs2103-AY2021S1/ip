package duke;

import duke.fxcommand.Command;
import duke.exception.DukeException;
import duke.utils.DukeState;

/**
 * The Duke object initializes the core classes: Ui, Storage, Parser and TaskList, and contains the main logic
 * that integrates them together to run the Duke application.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private DukeState dukeState;
    private final boolean isLoadedFromDisk;

    /**
     * Initializes a Duke object.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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

    public DukeState getDukeState() {
        return dukeState;
    }

    public boolean isLoadedFromDisk() {
        return isLoadedFromDisk;
    }
}
