package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command to save TaskList into file and exit Duke.
 */
public class ByeCommand implements Command {

    private final TaskList taskList;
    private final Storage storage;

    /**
     * Initialise ByeCommand with the taskList to store into storage
     * @param storage The storage object which handles file i/o.
     * @param taskList The taskList to be saved into storage.
     */
    public ByeCommand(Storage storage, TaskList taskList) {
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        StringJoiner responseBuilder = new StringJoiner("\n");
        if (storage.store(taskList)) {
            responseBuilder.add("Saved your list. Enter new command to exit...");
        } else {
            responseBuilder.add("Failed to save list. Enter new command to exit...");
        }
        return responseBuilder.toString();
    }

    @Override
    public boolean continueDuke() {
        return false;
    }
}
